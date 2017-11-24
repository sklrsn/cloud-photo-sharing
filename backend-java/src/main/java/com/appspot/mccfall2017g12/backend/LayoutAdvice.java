package com.appspot.mccfall2017g12.backend;

import com.samskivert.mustache.Mustache;
import com.samskivert.mustache.Template;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.io.IOException;
import java.io.Writer;

@ControllerAdvice
public class LayoutAdvice {

    @ModelAttribute("layout")
    public Mustache.Lambda layout() {
        return new Layout();
    }
}

class Layout implements Mustache.Lambda {
    public String body;

    @Override
    public void execute(Template.Fragment fragment, Writer writer) throws IOException {
        this.body = fragment.execute();
    }
}