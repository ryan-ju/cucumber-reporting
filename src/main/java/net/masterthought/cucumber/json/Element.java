package net.masterthought.cucumber.json;

import net.masterthought.cucumber.util.Function;
import net.masterthought.cucumber.util.Invocation;
import net.masterthought.cucumber.util.Util;
import org.apache.commons.lang.StringUtils;
import scala.Function1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Element {

    private String name;
    private String description;
    private String keyword;
    private Step[] steps;
    private Tag[] tags;

    public Element() {

    }

    public List<Step> getSteps() {
        return Arrays.asList(steps == null ? new Step[0] : steps);
    }

    public List<Tag> getTags() {
        return Arrays.asList(tags == null ? new Tag[0] : tags);
    }

    public Util.Status getStatus() {
        List<Step> results = Function.filter(getSteps(), Step.predicates.hasStatus(Util.Status.FAILED));
        return results.size() == 0 ? Util.Status.PASSED : Util.Status.FAILED;
    }

    public String getRawName() {
        return name;
    }

    public String getKeyword() {
        return keyword;
    }

    public String getName() {
        List<String> contentString = new ArrayList<String>();

        if (Util.itemExists(keyword)) {
            contentString.add("<span class=\"scenario-keyword\">" + keyword + ": </span>");
        }

        if (Util.itemExists(name)) {
            contentString.add("<span class=\"scenario-name\">" + name + "</span>");
        }

        return Util.itemExists(contentString) ? Util.result(getStatus()) + StringUtils.join(contentString.toArray(), " ") + Util.closeDiv() : "";
    }

    public List<String> getTagList() {
        return processTags();
    }

    public boolean hasTags() {
        return Util.itemExists(tags);
    }

    private List<String> processTags() {
        return Function.map(getTags(), Tag.functions.getName());
    }

    public String getTagsList() {
        String result = "<div class=\"feature-tags\"></div>";
        if (Util.itemExists(tags)) {
            String tagList = StringUtils.join(processTags().toArray(), ",");
            result = "<div class=\"feature-tags\">" + tagList + "</div>";
        }
        return result;
    }

    public static class functions {
        public static Function1<Element, Util.Status> status() {
            return Function.getInstance(new Invocation<Element, Util.Status>() {
                @Override
                public Util.Status call(Element element) {
                    return element.getStatus();
                }
            });
        }
    }

}
