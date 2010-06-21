/* Copyright 2008 Thomas Champagne
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.security.acls.domain;

import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.acls.model.Sid;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;


/**
 * Extended SidRetrievalStrategyImpl which uses a {@link RoleHierarchy} definition to determine the
 * roles allocated to the current user.
 * @author Thomas Champagne
 */
public class SidRoleHierarchyRetrievalStrategyImpl
    extends SidRetrievalStrategyImpl {
    private RoleHierarchy roleHierarchy = null;

    public SidRoleHierarchyRetrievalStrategyImpl(RoleHierarchy roleHierarchy) {
        Assert.notNull(roleHierarchy, "RoleHierarchy must not be null");
        this.roleHierarchy = roleHierarchy;
    }

    /**
     * Calls the <tt>RoleHierarchy</tt> to obtain the complete set of user authorities.
     */
    List<GrantedAuthority> extractAuthorities(Authentication authentication) {
        return roleHierarchy.getReachableGrantedAuthorities(authentication.getAuthorities());
    }

    public List<Sid> getSids(Authentication authentication) {
        List<GrantedAuthority> authorities = this.extractAuthorities(authentication);
        List<Sid> sids = new ArrayList<Sid>();

        sids.add(new PrincipalSid(authentication));

        for (int i = 1; i <= authorities.size(); i++) {
            sids.add(new GrantedAuthoritySid(authorities.get(i - 1)));
        }

        return sids;
    }
}
