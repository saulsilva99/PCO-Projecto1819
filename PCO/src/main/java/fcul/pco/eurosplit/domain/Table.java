package fcul.pco.eurosplit.domain;

import java.util.List;

/**
 *
 * @author tl
 */
public class Table {

    public static String tableToString(List<List<String>> table) {
        int[] columnsWidth = new int[table.get(0).size()];
        for (int j = 0; j < columnsWidth.length; j++) {
            int max = 0;
            for (List<String> row : table) {
                int w = row.get(j).length();
                if (w > max) {
                    max = w;
                }
            }
            columnsWidth[j] = max;
        }
        //---
        StringBuilder sb = new StringBuilder();
        for (int r = 0; r < table.size(); r++) {
            sb.append("+");
            for (int j = 0; j < columnsWidth.length; j++) {
                for (int i = 0; i < columnsWidth[j]; i++) {
                    sb.append("-");
                }
                sb.append("+");
            }
            sb.append("\n");
            for (int j = 0; j < columnsWidth.length; j++) {
                sb.append("|");
                String thing = table.get(r).get(j);
                sb.append(thing);
                for (int k = thing.length(); k < columnsWidth[j]; k++) {
                    sb.append(" ");
                }
            }
            sb.append("|\n");
        }
        sb.append("+");
        for (int j = 0; j < columnsWidth.length; j++) {
            for (int i = 0; i < columnsWidth[j]; i++) {
                sb.append("-");
            }
            sb.append("+");
        }
        sb.append("\n");
        return sb.toString();
    }
}
