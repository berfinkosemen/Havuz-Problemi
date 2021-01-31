
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;
import org.graphstream.ui.swingViewer.DefaultView;
import org.graphstream.ui.view.Viewer;

public class Gui1 extends JFrame {
    private JPanel root1;
    private JTextField textField1;
    private JButton btnSend;
    private JButton button1;
    private JTextPane textPane1;
    private JTextField textField2;
    private JLabel lbl, lbl2;
    private JButton button2;
    private JTextField textField3;
    private JFrame myJFrame;

    int a;
    String nodeBaglantisi;
    public int numOfNode = 0;
    List<JTextField> txts = new ArrayList<>();
    String baslangicDugumu, bitisDugumu;
    List<JButton> btns = new ArrayList<>();
    List<List<JTextField>> connections = new ArrayList<>();
    //List<List<JTextField>> connections_2 = new ArrayList<>();
    List<List<Integer>> baglantiMatrixi = new ArrayList<>();
    List<String> nodeNames = new ArrayList<>();
    List<Integer> nodeNums = new ArrayList<>();
    int say = 0;
    int say_2 = 0;
    int say_control = 0;


    public Gui1() {
        add(root1);

        setTitle("Havuz Problemi");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        root1.setBorder(new EmptyBorder(10, 10, 10, 10));
        lbl.setVisible(false);
        lbl2.setVisible(false);
        lbl.setText(" ");
        lbl2.setText("");
        button1.setVisible(false);
        textField2.setVisible(false);
        textField3.setVisible(false);
        button2.setVisible(false);

        btnSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                numOfNode = Integer.parseInt(textField1.getText());

                JPanel panel = new JPanel();
                JFrame frame = new JFrame();
                frame.add(panel);
                frame.setVisible(true);
                frame.getContentPane().setLayout(new FlowLayout());
                int wid = numOfNode * 50 + 350;

                if (wid > 5000) {
                    wid = 5000;
                }

                frame.setSize(wid, 150);
                frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                frame.setVisible(true);
                frame.setExtendedState(Frame.NORMAL);

                panel.setBorder(new EmptyBorder(10, 10, 10, 10));

                JLabel label = new JLabel();
                frame.add(label, BorderLayout.NORTH);
                label.setText("Musluk isimlerini girin");

                txts.clear();
                for (int i = 0; i < numOfNode; i++) {
                    JTextField textField = new JTextField();
                    txts.add(textField);
                    textField.setPreferredSize(new Dimension(50, 50));
                    textField.setMinimumSize(new Dimension(50, 50));
                    textField.setMaximumSize(new Dimension(50, 50));
                    frame.add(textField, BorderLayout.WEST);
                }

                JButton onaylaBtn = new JButton("Onayla");
                frame.getContentPane().add(onaylaBtn, BorderLayout.EAST);
                onaylaBtn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        frame.addWindowListener(new java.awt.event.WindowAdapter() {
                            @Override
                            public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                                frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                                writeNodeNames();

                                lbl.setVisible(true);
                                lbl2.setVisible(true);
                                button1.setVisible(true);
                                textField2.setVisible(true);
                                textField3.setVisible(true);
                                lbl.setText("Baslangic dugumunu sec");
                                lbl2.setText("Bitis dugumunu sec");
                            }
                        });

                        frame.dispose();
                    }
                });
            }
        });

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String dugum = textField2.getText();
                Boolean flag = false, flag2 = false;
                String dugum2 = textField3.getText();
                for (int i = 0; i < txts.size(); i++) {
                    if (txts.get(i).getText().equals(dugum)) {
                        flag = true;
                    }
                    if (txts.get(i).getText().equals(dugum2)) {
                        flag2 = true;
                    }
                }

                if (flag) {
                    baslangicDugumu = dugum;
                    lbl.setText("baslangic dugumu: " + baslangicDugumu);
                    button2.setVisible(true);
                } else {
                    lbl.setText("boyle bir dugum yok");
                }
                if (flag2) {
                    bitisDugumu = dugum2;
                    lbl2.setText("bitis dugumu: " + bitisDugumu);
                    button2.setVisible(true);
                } else {
                    lbl2.setText("boyle bir dugum yok");
                }
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel panel1 = new JPanel();
                JFrame frame1 = new JFrame();

                frame1.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                        connections.clear();
                        baglantiMatrixi.clear();

                    }
                });
                frame1.setVisible(true);
                frame1.getContentPane().setLayout(new GridLayout());
                int hei = numOfNode * 50 + 150;
                frame1.setSize(550, hei);
                frame1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame1.setExtendedState(Frame.NORMAL);

                panel1.setBorder(new EmptyBorder(10, 10, 10, 10));

                JLabel label = new JLabel();
                label.setText("Musluk Adları");

                btns.clear();
                JPanel layout = new JPanel(new GridBagLayout());
                layout.setBorder(new EmptyBorder(5, 5, 5, 5));

                JPanel btnPanel = new JPanel(new GridLayout(10, 1, 10, 5));
                btnPanel.add(label);
                for (int i = 0; i < numOfNode; i++) {
                    JButton button = new JButton();
                    btns.add(button);
                    button.setText(txts.get(i).getText());
                    button.setPreferredSize(new Dimension(50, 50));
                    button.setMinimumSize(new Dimension(50, 50));
                    button.setMaximumSize(new Dimension(50, 50));
                    btnPanel.add(button);

                    a = i;
                    button.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            String str = "";

                            for (int p = 0; p < connections.size(); p++) {
                                if (connections.get(p).get(0).getText().equals(button.getText())) {
                                    str += connections.get(p).get(0).getText() + " " + connections.get(p).get(1).getText() + " "
                                            + connections.get(p).get(2).getText() + " " + connections.get(p).get(3).getText();
                                }
                            }

                            JPanel pnl = new JPanel();
                            JFrame frm = new JFrame();
                            frm.add(pnl);
                            frm.setVisible(true);
                            frm.getContentPane().setLayout(new FlowLayout());

                            frm.setSize(350, 150);
                            frm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                            frm.setVisible(true);
                            frm.setExtendedState(Frame.NORMAL);

                            pnl.setBorder(new EmptyBorder(10, 10, 10, 10));

                            String[] columnsNames = {"kaynakDugum", "HedefDugum", "KenarAdi", "Kapasite"};
                            List<List<Object>> dataList = new ArrayList<>();

                            for (int p = 0; p < connections.size(); p++) {

                                if (connections.get(p).get(0).getText().equals(button.getText())) {

                                    List<Object> list = new ArrayList<>();
                                    list.add(connections.get(p).get(0).getText());
                                    list.add(connections.get(p).get(1).getText());
                                    list.add(connections.get(p).get(2).getText());
                                    list.add(connections.get(p).get(3).getText());

                                    dataList.add(list);
                                }
                            }

                            Object[][] data = new Object[dataList.size()][4];

                            for (int p = 0; p < dataList.size(); p++) {
                                data[p][0] = dataList.get(p).get(0);
                                data[p][1] = dataList.get(p).get(1);
                                data[p][2] = dataList.get(p).get(2);
                                data[p][3] = dataList.get(p).get(3);
                            }

                            JTable t = new JTable(data, columnsNames);
                            frm.add(t.getTableHeader(), BorderLayout.PAGE_START);
                            frm.add(t, BorderLayout.CENTER);
                            frm.setVisible(true);
                            frm.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

                        }
                    });
                }
                layout.add(btnPanel);
                panel1.add(layout, BorderLayout.WEST);
                frame1.add(panel1);

                JPanel panel2 = new JPanel();
                panel2.setBorder(new EmptyBorder(10, 10, 10, 10));

                JPanel layout2 = new JPanel(new GridBagLayout());
                layout.setBorder(new EmptyBorder(5, 5, 5, 5));

                JPanel btnPanel2 = new JPanel(new GridLayout(10, 1, 10, 5));

                JButton button = new JButton();
                button.setText("Bağlantı ekle");
                btnPanel2.add(button);


                JTextField textField = new JTextField();
                textField.setVisible(false);

                JLabel label1 = new JLabel();
                label1.setText("Hangi node için bağlantı eklenecek?");
                label1.setVisible(false);

                btnPanel2.add(label1);
                btnPanel2.add(textField);

                JButton ekleBtn = new JButton();
                ekleBtn.setText("Ekle");
                ekleBtn.setVisible(false);

                JButton devamEtBtn = new JButton();
                devamEtBtn.setText("Tamamla");

                JButton button3 = new JButton();
                button3.setText("Görselleştir");

                btnPanel2.add(ekleBtn);
                btnPanel2.add(devamEtBtn, BorderLayout.SOUTH);
                btnPanel2.add(button3, BorderLayout.SOUTH);

                layout2.add(btnPanel2);
                panel2.add(layout2, BorderLayout.EAST);
                frame1.add(panel2);

                devamEtBtn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        baglantiMatrixiOlustur();
                        findMaxFLow();
                    }
                });

                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        textField.setVisible(true);
                        label1.setVisible(true);
                        ekleBtn.setVisible(true);
                    }
                });


                JPanel allPanel = new JPanel();
                allPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

                JPanel allLayout = new JPanel(new GridBagLayout());
                allLayout.setBorder(new EmptyBorder(5, 5, 5, 5));

                JPanel allButons = new JPanel(new GridLayout(100, 1, 10, 5));

                JFrame frame2 = new JFrame();
                JButton satirEkle = new JButton();
                JPanel panel3 = new JPanel();
                JPanel layout3 = new JPanel(new GridBagLayout());

                JButton btnOk = new JButton();
                btnOk.setText("Tamam");

                ekleBtn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        nodeBaglantisi = textField.getText();

                        frame2.setVisible(true);
                        frame2.getContentPane().setLayout(new GridLayout());
                        int hei = numOfNode * 50 + 150;
                        frame2.setSize(550, hei);
                        frame2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        frame2.setExtendedState(Frame.NORMAL);

                        panel3.setBorder(new EmptyBorder(10, 10, 10, 10));
                        layout3.setBorder(new EmptyBorder(5, 5, 5, 5));

                        JPanel btnPanel3 = new JPanel(new GridLayout(1, 5, 10, 5));

                        JLabel bagdugum = new JLabel();
                        bagdugum.setText("Hangi dugum");

                        JLabel kenAdi = new JLabel();
                        kenAdi.setText("Kenar adi");

                        JLabel cap = new JLabel();
                        cap.setText("kapasite");

                        btnPanel3.add(bagdugum);
                        btnPanel3.add(kenAdi);
                        btnPanel3.add(cap);

                        satirEkle.setText("Satır ekle");

                        btnPanel3.add(satirEkle);
                        btnPanel3.add(btnOk);
                        layout3.add(btnPanel3);
                        panel3.add(layout3, BorderLayout.EAST);

                        allButons.add(btnPanel3);
                        allLayout.add(allButons);
                        allPanel.add(allLayout, BorderLayout.CENTER);
                        frame2.add(allPanel);
                    }
                });

                connections = new ArrayList<>();

                satirEkle.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JPanel pan = new JPanel();
                        pan.setBorder(new EmptyBorder(10, 10, 10, 10));

                        JPanel lay = new JPanel(new GridBagLayout());
                        lay.setBorder(new EmptyBorder(5, 5, 5, 5));

                        JPanel btnPan = new JPanel(new GridLayout(1, 4, 10, 5));

                        JTextField jTextField = new JTextField();
                        JTextField jTextField1 = new JTextField();
                        JTextField jTextField2 = new JTextField();
                        JTextField jTextField3 = new JTextField();

                        jTextField.setPreferredSize(new Dimension(50, 50));
                        jTextField.setMinimumSize(new Dimension(50, 50));
                        jTextField.setMaximumSize(new Dimension(50, 50));
                        jTextField.setText(nodeBaglantisi);

                        jTextField1.setPreferredSize(new Dimension(50, 50));
                        jTextField1.setMinimumSize(new Dimension(50, 50));
                        jTextField1.setMaximumSize(new Dimension(50, 50));

                        jTextField2.setPreferredSize(new Dimension(50, 50));
                        jTextField2.setMinimumSize(new Dimension(50, 50));
                        jTextField2.setMaximumSize(new Dimension(50, 50));

                        jTextField3.setPreferredSize(new Dimension(50, 50));
                        jTextField3.setMinimumSize(new Dimension(50, 50));
                        jTextField3.setMaximumSize(new Dimension(50, 50));

                        btnPan.add(jTextField);
                        btnPan.add(jTextField1);
                        btnPan.add(jTextField2);
                        btnPan.add(jTextField3);

                        lay.add(btnPan);
                        pan.add(lay, BorderLayout.WEST);

                        List<JTextField> lst = new ArrayList<>();
                        lst.add(jTextField);
                        lst.add(jTextField1);
                        lst.add(jTextField2);
                        lst.add(jTextField3);

                        connections.add(lst);

                        jTextField2.setText("k" + connections.size());

                        allButons.add(pan);
                        allLayout.add(allButons);
                        allPanel.add(allLayout, BorderLayout.WEST);
                        frame2.add(allPanel);
                        frame2.revalidate();

                        frame2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        frame2.addWindowListener(new java.awt.event.WindowAdapter() {
                            @Override
                            public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                                layout3.removeAll();
                                panel3.removeAll();

                                allButons.removeAll();
                                allLayout.removeAll();
                                allPanel.removeAll();
                            }
                        });

                    }

                });

                btnOk.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        frame2.dispose();
                    }
                });

                button3.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed (ActionEvent e){

                        List<Integer> queue_2 = new ArrayList<>();
                        List<Integer> flow = new ArrayList<>();
                        List<Integer> queue_correct_order = new ArrayList<>();
                        List<String> queue_names = new ArrayList<>();
                        List<Integer> flow_change = new ArrayList<>();
                        List<String> edge_change = new ArrayList<>();
                        List<String> connections_2 = new ArrayList<>();
                        List<String> connections_3 = new ArrayList<>();
                        List<String> connections_4 = new ArrayList<>();


                        MaxFlow m = new MaxFlow();

                        flow.addAll(m.flow_list(find_graph(),nodeNames.indexOf(baslangicDugumu), nodeNames.indexOf(bitisDugumu)));
                        queue_2.addAll(m.queue_list(find_graph(),nodeNames.indexOf(baslangicDugumu), nodeNames.indexOf(bitisDugumu)));

                        for(int i=0;i<queue_2.size();i++){
                            if(queue_2.get(i) == 0){
                                for(int j=i; j>=0;j--){
                                    if(queue_2.get(j) == 0 && j != i){
                                        break;
                                    }
                                    queue_correct_order.add(queue_2.get(j));
                                }
                            }
                        }

                        for (int p = 0; p < connections.size(); p++) {
                            connections_2.add(connections.get(p).get(0).getText());
                            connections_2.add(connections.get(p).get(1).getText());
                            connections_2.add(connections.get(p).get(2).getText());
                            connections_2.add(connections.get(p).get(3).getText());
                            connections_4.add(connections.get(p).get(3).getText());
                        }


                        int i=0,j,t=0, k=0;

                        for (i=0; i<queue_correct_order.size(); i++){
                            queue_names.add(nodeNames.get(queue_correct_order.get(i)));
                        }

                        for(i=0; i<queue_names.size()-1; i++){
                            for(j=0; j< connections_2.size()-1;j+=4){
                                if(queue_names.get(i).equals(connections_2.get(j)) && queue_names.get(i+1).equals(connections_2.get(j+1)) && !queue_names.get(i+1).equals(baslangicDugumu)){
                                    edge_change.add(connections_2.get(j+2));
                                    flow_change.add(flow.get(t));
                                    connections_3.add(connections_2.get(j+3));
                                }
                                else if(queue_names.get(i+1).equals(baslangicDugumu) && j==0){
                                    t++;
                                }
                            }
                        }

                        for(i=0; i<edge_change.size()-1; i++){
                            for(j=i+1; j<edge_change.size(); j++){
                                if(edge_change.get(i).equals(edge_change.get(j))){
                                    flow_change.set(j,flow_change.get(i) + flow_change.get(j));
                                    break;
                                }
                            }
                        }

                        final DefaultGraph graph = new DefaultGraph("my beautiful graph");
                        graph.addAttribute("ui.stylesheet", styleSheet);
                        graph.setStrict(false);
                        graph.setAutoCreate(true);

                        Viewer viewer = new Viewer(graph, Viewer.ThreadingModel.GRAPH_IN_GUI_THREAD);
                        myJFrame = new JFrame();
                        myJFrame.setPreferredSize(new Dimension(800, 700));
                        DefaultView view = (DefaultView) viewer.addDefaultView(false);   // false indicates "no JFrame".
                        view.setPreferredSize(new Dimension(600, 600));
                        myJFrame.setLayout(new FlowLayout());
                        myJFrame.add(view);
                        JButton myButton = new JButton("MyButton");
                        myJFrame.add(myButton);
                        myJFrame.pack();
                        myJFrame.setVisible(true);
                        myJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        viewer.enableAutoLayout();


                        for(i=0;i<numOfNode;i++){
                            graph.addNode(nodeNames.get(i));
                        }


                        for (int p = 0; p < connections.size(); p++) {
                            graph.addEdge(connections.get(p).get(2).getText(), connections.get(p).get(0).getText(), connections.get(p).get(1).getText(),true);
                        }

                        for (Node node : graph) {
                            node.addAttribute("ui.label", node.getId());
                        }

                        int ii=0;
                        for(Edge edge : graph.getEachEdge()) {
                            edge.addAttribute("ui.label", "0/" +connections_4.get(ii));
                            ii++;
                        }

                        myButton.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {

                                if(queue_names.size() != say){
                                    if(queue_names.get(say).equals(baslangicDugumu) && say != 0){
                                        say_control++;
                                    }
                                }

                                if(queue_names.size() == say){
                                    for(Node node : graph){
                                        node.setAttribute("ui.class", node.getId());
                                    }
                                }

                                if(say_control%2 == 0 && queue_names.size() != say){

                                    if(queue_names.get(say).equals(baslangicDugumu) && say != 0){
                                        for(Node node : graph){
                                            node.setAttribute("ui.class", node.getId());
                                        }
                                    }


                                    for (Node node : graph) {
                                        if (queue_names.get(say).equals(node.getId())){
                                            node.setAttribute("ui.class", "marked", node.getId());
                                            break;
                                        }
                                    }

                                    say++;
                                }
                                else if(say_control%2 != 0 && edge_change.size() != say_2){

                                    for (Edge edge : graph.getEachEdge()) {
                                        if(edge_change.get(say_2).equals(edge.getId())){
                                            edge.setAttribute("ui.label", " " + flow_change.get(say_2) + "/" +connections_3.get(say_2));
                                        }
                                    }
                                    say_2++;
                                }
                                say_control++;
                            }
                        });
                    }
                });
            }
        });
    }


    public void writeNodeNames() {
        String str = "node names :\n";
        for (int i = 0; i < txts.size(); i++) {
            str += txts.get(i).getText() + " ";
            nodeNames.add(txts.get(i).getText());
            nodeNums.add(i);
        }
        textPane1.setText(str);
    }


    public void baglantiMatrixiOlustur() {
        for (int p = 0; p < numOfNode; p++) {

            List<Integer> lst = new ArrayList<>();
            for (int q = 0; q < numOfNode; q++) {
                lst.add(0);
            }
            baglantiMatrixi.add(lst);
        }

        for (int p = 0; p < connections.size(); p++) {
            for (int q = 0; q < connections.size(); q++) {
                int node1 = nodeNames.indexOf(connections.get(p).get(0).getText());
                int node2 = nodeNames.indexOf(connections.get(p).get(1).getText());

                baglantiMatrixi.get(node1).set(node2, Integer.parseInt(connections.get(p).get(3).getText()));
            }
        }
    }


    public void findMaxFLow() {
        MaxFlow m = new MaxFlow();
        m.V = baglantiMatrixi.size();

        String graf = "";

        int[][] graph = new int[baglantiMatrixi.size()][baglantiMatrixi.size()];

        System.out.println("Graph: ");
        for (int p = 0; p < baglantiMatrixi.size(); p++) {
            for (int q = 0; q < baglantiMatrixi.size(); q++) {
                graph[p][q] = baglantiMatrixi.get(p).get(q);
                System.out.print(graph[p][q] + " ");
                graf += graph[p][q] + " ";
            }
            graf += "\n";
            System.out.println();
        }

        graf += "\n";

        System.out.println();

        System.out.println("The maximum possible flow is " +
                m.fordFulkerson(graph, nodeNames.indexOf(baslangicDugumu), nodeNames.indexOf(bitisDugumu)));

        System.out.println();

        graf += "The maximum possible flow is " + "\n" + m.fordFulkerson(graph, nodeNames.indexOf(baslangicDugumu), nodeNames.indexOf(bitisDugumu));
        MinCut mc = new MinCut();

        graf += "\n";
        graf+="Minimum cut is \n";

        System.out.println("min cut");
        String str =  mc.minCut(graph, nodeNames.indexOf(baslangicDugumu), nodeNames.indexOf(bitisDugumu));
        List<String> lst = new ArrayList<>();
        int k=0;
        String a = "";
        while(k+5 < str.length()) {

            lst.add(Character.toString(str.charAt(k)));
            lst.add(Character.toString(str.charAt(k+4)));

            String x1 = nodeNames.get(Integer.parseInt(Character.toString(str.charAt(k))));
            String x2 = nodeNames.get(Integer.parseInt(Character.toString(str.charAt(k+4))));

            a += x1;
            a += " - ";
            a += x2;

            a+=" ";
            for (int p = 0; p < connections.size(); p++) {
                if (connections.get(p).get(0).getText().equals(x1) && connections.get(p).get(1).getText().equals(x2)) {
                    a += connections.get(p).get(2).getText().toString();
                }
            }

            a+="\n";

            k += 6;
        }
        graf += a;

        graf +="\n";

        JPanel panel = new JPanel();
        JFrame frame = new JFrame();

        frame.setVisible(true);
        frame.getContentPane().setLayout(new GridLayout());
        frame.setSize(350, 350);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setExtendedState(Frame.NORMAL);
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        JPanel layout = new JPanel(new GridBagLayout());
        layout.setBorder(new EmptyBorder(5, 5, 5, 5));
        JPanel btnPanel = new JPanel(new GridLayout(10, 1, 10, 5));


        JTextPane label = new JTextPane();
        label.setText(graf);

        btnPanel.add(label, BorderLayout.CENTER);
        layout.add(btnPanel);
        panel.add(layout, BorderLayout.EAST);
        frame.add(panel);

    }

    public int[][] find_graph() {
        MaxFlow m = new MaxFlow();
        m.V = baglantiMatrixi.size();

        String graf = "", flow="", cut="";

        int[][] graph = new int[baglantiMatrixi.size()][baglantiMatrixi.size()];

        for (int p = 0; p < baglantiMatrixi.size(); p++) {
            for (int q = 0; q < baglantiMatrixi.size(); q++) {
                graph[p][q] = baglantiMatrixi.get(p).get(q);
                graf += graph[p][q] + " ";
            }
            graf += "\n";
        }

        return graph;
    }

    protected String styleSheet =
            "node {" +
                    "   size: 60px;"+
                    "	fill-color: green;" +
                    "text-alignment: left;"+
                    "   text-color: #222;"+
                    "   text-size:25;"+
                    "}" +
                    "node.marked {" +
                    "	fill-color: red;" +
                    "}"+
                    "edge { z-index: 0; fill-color: #333; size: 5px; text-alignment: above; text-color: #222; text-size:20;arrow-shape: arrow; arrow-size: 10px, 10px;}";

}

