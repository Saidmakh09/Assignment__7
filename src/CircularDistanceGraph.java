import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.view.Viewer;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CircularDistanceGraph {
    public static void main(String[] args) {
        System.setProperty("org.graphstream.ui", "swing");

        // ——— Change this to your input string ———
        String input = "[ (I, 2) , (A, 5) , (E, 4) , (F,2) , (T, 2) , (S, 3) ]";

        // 1) Parse
        Pattern p = Pattern.compile("\\(\\s*([A-Za-z0-9]+)\\s*,\\s*(\\d+)\\s*\\)");
        Matcher m = p.matcher(input);
        List<String> verts = new ArrayList<>();
        List<Integer> offs = new ArrayList<>();
        while (m.find()) {
            verts.add(m.group(1));
            offs.add(Integer.parseInt(m.group(2)));
        }

        int n = verts.size();
        if (n == 0) {
            System.out.println("Empty graph — nothing to display.");
            return;
        }

        // 2) Build graph
        Graph graph = new SingleGraph("CircularDistance");
        graph.setAttribute("ui.stylesheet",
                "node { fill-color: white; size: 40px; text-size: 20px; }" +
                        "edge { arrow-size: 15px, 10px; }"
        );

        // add nodes
        for (String v : verts) {
            Node node = graph.addNode(v);
            node.setAttribute("ui.label", v);
        }

        // add edges (right _R and left _L, skipping duplicates)
        for (int i = 0; i < n; i++) {
            String v = verts.get(i);
            int x = offs.get(i) % n;
            int r = (i + x) % n;
            int l = (i - x + n) % n;

            String idR = v + "_to_" + verts.get(r) + "_R";
            graph.addEdge(idR, v, verts.get(r), true);

            if (l != r) {
                String idL = v + "_to_" + verts.get(l) + "_L";
                graph.addEdge(idL, v, verts.get(l), true);
            }
        }

        // Diplaying using Swing
        Viewer viewer = graph.display();
        viewer.setCloseFramePolicy(Viewer.CloseFramePolicy.EXIT);
    }
}
