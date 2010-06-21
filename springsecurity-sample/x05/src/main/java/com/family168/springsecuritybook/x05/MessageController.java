package com.family168.springsecuritybook.x05;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.Writer;


@Controller
public class MessageController {
    @RequestMapping(value = "/messages", method = RequestMethod.GET)
    public void messages() {
    }

    @RequestMapping(value = "/messages/{messageId}", method = RequestMethod.GET)
    public void view(@PathVariable
    Long messageId, Writer out) throws Exception {
        System.out.println("view: " + messageId);
        out.write("{id:1,title:'message'}");
    }

    @RequestMapping(value = "/messages", method = RequestMethod.POST)
    public void create(Message message, Writer out) throws Exception {
        System.out.println("create: " + message);
        out.write("{success:true}");
    }

    @RequestMapping(value = "/messages/{messageId}", method = RequestMethod.PUT)
    public void update(@PathVariable
    Long messageId, Message message, Writer out) throws Exception {
        System.out.println("update: " + messageId + "," + message);

        out.write("{success:true}");
    }

    @RequestMapping(value = "/messages/{messageId}", method = RequestMethod.DELETE)
    public void remove(@PathVariable
    Long messageId, Writer out) throws Exception {
        System.out.println("delete: " + messageId);

        out.write("{success:true}");
    }
}
