package md2html;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Strikeout extends Markup {
    Strikeout() {
        super();

        htmlBegin = "<s>";
        htmlEnd = "</s>";
    }
}
