import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

        public class Main {
            private static JFrame frame;

            public static void main(String[] args) {
                SwingUtilities.invokeLater(() -> new Main().createAndShowGUI());
            }

            Action start = new AbstractAction()
            {
                public void actionPerformed(ActionEvent e)
                {

                    JTable table = (JTable)e.getSource();
                    DefaultTableModel model= (DefaultTableModel) table.getModel();
                    int modelRow = Integer.parseInt(e.getActionCommand());
                    String aircraft = (String) model.getValueAt(modelRow,0);
                    MyRunnable thread = new MyRunnable(aircraft,model);
                    ExecutorService executorService = Executors.newFixedThreadPool(Integer.MAX_VALUE);
                    executorService.execute(thread);

                    }

            };

            public static boolean isModelinarr(String model, ArrayList<Aircraft> planes) {
                for (Aircraft aircraft : planes) {
                    if (aircraft.getModel().equals(model)) {
                        return true;
                    }
                }
                return false;
            }

            public JButton getjButton3(JTextField modelplane,JTextField capacityfield,JTextField speedfield,JRadioButton CargoPlane,JRadioButton PassangerPlane, DefaultTableModel model,ArrayList<Aircraft>planes ) {
                JButton button = new JButton("Dodaj samolot");
                button.addActionListener(_ -> {
                  Vector <String> v = new Vector<>();
                  String mod=modelplane.getText();
                  int capacity=Integer.parseInt(capacityfield.getText());
                  int speed=Integer.parseInt(speedfield.getText());

                  if(CargoPlane.isSelected()) {
                      CargoPlane cargoPlane=new CargoPlane(mod,capacity,speed);
                      if (isModelinarr(mod, planes)) {
                          System.out.println("Dany samolot istnieje");
                      } else {
                          planes.add(cargoPlane);
                          v.add(mod);
                          v.add("CargoPlane");
                          v.add("  ");
                          v.add("Startuj ");
                          model.addRow(v);
                      }
                  }
                  if(PassangerPlane.isSelected()) {
                      PassangerPlane passangerPlane=new PassangerPlane(mod,capacity,speed);
                      if (isModelinarr(mod, planes)) {
                          System.out.println("Dany samolot istnieje");
                      } else {
                          planes.add(passangerPlane);
                          v.add(mod);
                          v.add("PassangerPlane");
                          v.add(" ");
                          v.add(" Wystartuj ");
                          model.addRow(v);
                      }
                  }
                });
                return button;
            }

            private  JButton getjButton1(DefaultTableModel model,ArrayList<Aircraft>planes ) {
                JButton button = new JButton("Dodaj samolot");
                button.addActionListener(e -> {
                    JFrame frame1 = new JFrame("Input");
                    frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame1.setSize(500, 500);
                    frame1.setLayout(new BorderLayout(8, 8));

                    JPanel basicPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
                    JRadioButton jRadioButton1 = new JRadioButton("Cargo Plane");
                    JRadioButton jRadioButton2 = new JRadioButton("PassangerPlane");
                    ButtonGroup buttonGroup = new ButtonGroup();
                    buttonGroup.add(jRadioButton1);
                    buttonGroup.add(jRadioButton2);

                    frame1.add(basicPanel, BorderLayout.NORTH);
                    JTextField textField = new JTextField(15);
                    JTextField textField1 = new JTextField(15);
                    JTextField textField2 = new JTextField(15);
                    JButton button1 = getjButton3(textField,textField1,textField2,jRadioButton1,jRadioButton2, model,planes);
                    JButton button2 = getjButton2(frame1);
                   JLabel label = new JLabel("Podaj dane samolotu ( 1 typ, 2 pojemność, 3 prędkość): ");
                    Box box = Box.createVerticalBox();
                    box.add(label);

                    box.add(textField);
                    box.add(textField1);
                    box.add(textField2);
                    box.add(jRadioButton1);
                    box.add(jRadioButton2);
                    box.add(button1);
                    box.add(button2);
                    basicPanel.add(box);
                    frame1.setVisible(true);
                });
                return button;
            }

            private static JButton getjButton2(JFrame frame) {
                JButton button = new JButton("Cancel");
                button.addActionListener(e -> frame.dispose());
                return button;
            }

            private void createAndShowGUI() {
                frame = new JFrame("AirportApp");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(900, 600);
                frame.setLayout(new BorderLayout(8, 8));
                JPanel basicPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

                frame.add(basicPanel, BorderLayout.EAST);
                DefaultTableModel model = new DefaultTableModel();
                ArrayList<Aircraft> planes = new ArrayList<>();


                model.addColumn("Model");
                model.addColumn("Typ");
                model.addColumn("Status");
                model.addColumn("Akcja");


                Box box = Box.createVerticalBox();
                JButton btn1 = getjButton1(model,planes);
                box.add(btn1);
                basicPanel.add(box);
                frame.add(basicPanel, BorderLayout.EAST);
                JTable table = new JTable();
                table.setModel(model);
                ButtonColumn buttonColumn = new ButtonColumn(table, start, 3);
                JScrollPane tableScroll = new JScrollPane(table);
                table.setRowHeight(100);
                frame.add(tableScroll, BorderLayout.CENTER);
                frame.setVisible(true);
                JPanel basicPanel1 = new JPanel(new FlowLayout(FlowLayout.CENTER));

                frame.add(basicPanel1, BorderLayout.NORTH);
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

                    for (int k = 0; k <3 ; k++) {
                        String status="";
                        String modelsam="";
                        if(k==0){
                            status = "startuje";
                        }
                        if(k==1){
                            status = "w locie";
                        }
                        if(k==2){
                            status = "wylądował";
                        }
                        int row = 0;
                        int col = 0;
                        for (int i = 0; i < model.getRowCount(); i++) {
                            for (int j = 0; j < model.getColumnCount(); j++) {
                                if (model.getValueAt(i, j).equals(threadName)) {
                                    row = i;
                                    col = j + 2;
                                    modelsam = model.getValueAt(i, 0).toString();
                                }
                            }
                        }
                        model.setValueAt(status, row, col);
                        System.out.println(modelsam + ":"+status);
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            System.out.println(threadName + " przerwany!");
                        }
                    }
                }
            }
        }


