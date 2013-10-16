package net.masterthought.cucumber.json;

import net.masterthought.cucumber.util.Function;
import net.masterthought.cucumber.util.Invocation;
import scala.Function1;

public class Tag {

    private String name;

    public Tag(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static class functions {

        public static Function1<Tag, String> getName() {
            return Function.getInstance(new Invocation<Tag, String>(){
                @Override
                public String call(Tag tag) {
                    return tag.getName();
                }
            });
        }
    }

}
