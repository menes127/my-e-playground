/* Copyright 2004, 2005, 2006 Acegi Technology Pty Limited
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
package org.springframework.security.captcha;


/**
 * Provide a common interface for captcha validation.
 *
 * @author Marc-Antoine Garrigue
 * @version $Id: CaptchaServiceProxy.java 2743 2008-03-17 11:06:32Z luke_t $
 */
public interface CaptchaServiceProxy {
    /**
     *
     * @param id the id token
     * @param captchaResponse the user response
     *
     * @return true if the response is validated by the back end captcha service.
     */
    boolean validateReponseForId(String id, Object captchaResponse);
}
