package com.brobot.brobotREST.web.controllers;

import com.brobot.brobotREST.primatives.enums.StateEnum;
import com.brobot.brobotREST.reports.Tree;
import com.brobot.brobotREST.web.services.TreeService;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Component
public class TreeController {

    private TreeService treeService;

    public TreeController(TreeService treeService) {
        this.treeService = treeService;
    }

    @PostMapping("/brobot/tree/{id}")
    public String getTree(Model model, StateEnum startState, @PathVariable Long id, Principal p) {
        //Person user = personService.get(p);
        Tree tree = treeService.makeTree(startState);
        model.addAttribute(tree);
        return "";
    }

}
