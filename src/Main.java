import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

        public class Main {
            public static int num=0;
            private static JFrame frame;
            public static int charindx=0;
            public static void main(String[] args) {
                SwingUtilities.invokeLater(() -> new Main().createAndShowGUI());
            }


            public static JButton getjButton(DefaultTableModel model,ArrayList<MyRunnable>threads ) {
                JButton button = new JButton("Uruchom wątki");
                button.addActionListener(e -> {
                    for(int i=0;i<threads.size();i++) {

                        String s="";
                        MyRunnable thread = threads.get(i);
                        thread = new MyRunnable(thread.getThreadName(),model);
                        ExecutorService executorService = Executors.newFixedThreadPool(threads.size());
                        executorService.execute(thread);
                    }
                });
                return button;
            }

            public static JButton getjButton1(DefaultTableModel model, ArrayList<MyRunnable> threads, JLabel label) {
                JButton button = new JButton("Dodaj wątek");
                button.addActionListener(e -> {
                    String aplhabet="ABCDEFGHIJKMNLOPQRSTUVWXYZ";


                    if(charindx>=25){
                        charindx=charindx-25;
                    }
                    char char1=aplhabet.charAt(charindx);
                    charindx++;
                    num++;
                    String s="0";
                    MyRunnable runnable = new MyRunnable( "Wątek "+ char1,model);
                    label.setText("Liczba wątków " + num);
                    Vector<String> v=new Vector<>();
                    v.add(runnable.getThreadName());
                    v.add(s);
                    model.addRow(v);
                    threads.add(runnable);
                });
                return button;
            }

            private void createAndShowGUI() {
                frame = new JFrame("Aplikacja wątki");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(900, 600);
                frame.setLayout(new BorderLayout(8, 8));
                JPanel basicPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

                frame.add(basicPanel, BorderLayout.EAST);
                DefaultTableModel model = new DefaultTableModel();
                ArrayList<MyRunnable> threads = new ArrayList<>();


                model.addColumn("Wątki");
                model.addColumn("Status");



                JButton btn = getjButton(model,threads);
                Box box = Box.createVerticalBox();
                basicPanel.add(btn);
                JLabel label = new JLabel("Liczba wątków: 0");
                JButton btn1 = getjButton1(model, threads, label);
                box.add(btn1);
                box.add(btn);
                basicPanel.add(box);
                frame.add(basicPanel, BorderLayout.EAST);
                JTable table = new JTable();
                table.setModel(model);
                JScrollPane tableScroll = new JScrollPane(table);
                table.setRowHeight(100);
                frame.add(tableScroll, BorderLayout.CENTER);
                frame.setVisible(true);
                JPanel basicPanel1 = new JPanel(new FlowLayout(FlowLayout.CENTER));

                frame.add(basicPanel1, BorderLayout.NORTH);
                basicPanel1.add(label);
            }

            static class MyRunnable implements Runnable {
                private final String threadName;
                private final DefaultTableModel model;


                public MyRunnable(String name, DefaultTableModel model) {
                    this.threadName = name;
                    this.model = model;
                }

                public DefaultTableModel getModel() {
                    return model;
                }
                public String getThreadName(){
                    return threadName;
                }
                @Override
                public void run() {
                    for (int k = 1; k <= 10; k++) {
                        int row=0;
                        int col=0;
                        for (int i = 0; i < model.getRowCount(); i++) {
                            for (int j = 0; j < model.getColumnCount(); j++) {
                                if (model.getValueAt(i, j).equals(threadName)) {
                                    row=i;
                                    col=j+1;
                                }
                            }
                        }
                        model.setValueAt(k, row, col);
                        try {
                            Thread.sleep(300);
                        } catch (InterruptedException e) {
                            System.out.println(threadName + " przerwany!");
                        }
                    }
                }
            }
        }

