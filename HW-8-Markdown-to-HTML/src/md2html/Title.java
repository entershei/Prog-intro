package md2html;

import java.util.Collections;
import java.util.List;

public class Title extends Markup {
    int level;

    Title(int k) {
        super();
        level = k;

        htmlBegin = "<h" + k + ">";
        htmlEnd = "</h" + k + ">\n";
    }
}
