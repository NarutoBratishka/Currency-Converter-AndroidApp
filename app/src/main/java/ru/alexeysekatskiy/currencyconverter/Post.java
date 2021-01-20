package ru.alexeysekatskiy.currencyconverter;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;


@Root(strict = false)
@Element(name = "ValCurs")
public class Post {

    @ElementList(inline = true, name = "Valute")
    public List<PostElement> element;

    public List<PostElement> getElement() {
        return element;
    }

    public void setElement(List<PostElement> element) {
        this.element = element;
    }

    public Post() { }
}
