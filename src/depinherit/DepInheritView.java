/*
 * DepInheritView.java
 */

package depinherit;

import Business.Asset;
import Business.AssetDDL;
import Business.AssetSL;
import org.jdesktop.application.Action;
import org.jdesktop.application.ResourceMap;
import org.jdesktop.application.SingleFrameApplication;
import org.jdesktop.application.FrameView;
import org.jdesktop.application.TaskMonitor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import javax.swing.Timer;
import javax.swing.Icon;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 * The application's main frame.
 */
public class DepInheritView extends FrameView {
    
    AssetSL assetSL;
    AssetDDL assetDDL;

    public DepInheritView(SingleFrameApplication app) {
        super(app);

        initComponents();
        tbl_schedule.setName(null);
     //   DefaultTableCellRenderer rend = (DefaultTableCellRenderer) tbl_schedule.getDefaultRenderer(Object.class);
     //   rend.setHorizontalAlignment(JLabel.RIGHT);
        
   // add buttons to radio group
        methodGroup.add(rdo_straightLine);
        methodGroup.add(rdo_doubleDeclining);

        // status bar initialization - message timeout, idle icon and busy animation, etc
        ResourceMap resourceMap = getResourceMap();
        int messageTimeout = resourceMap.getInteger("StatusBar.messageTimeout");
        messageTimer = new Timer(messageTimeout, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                statusMessageLabel.setText("");
            }
        });
        messageTimer.setRepeats(false);
        int busyAnimationRate = resourceMap.getInteger("StatusBar.busyAnimationRate");
        for (int i = 0; i < busyIcons.length; i++) {
            busyIcons[i] = resourceMap.getIcon("StatusBar.busyIcons[" + i + "]");
        }
        busyIconTimer = new Timer(busyAnimationRate, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                busyIconIndex = (busyIconIndex + 1) % busyIcons.length;
                statusAnimationLabel.setIcon(busyIcons[busyIconIndex]);
            }
        });
        idleIcon = resourceMap.getIcon("StatusBar.idleIcon");
        statusAnimationLabel.setIcon(idleIcon);
        progressBar.setVisible(false);

        // connecting action tasks to status bar via TaskMonitor
        TaskMonitor taskMonitor = new TaskMonitor(getApplication().getContext());
        taskMonitor.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                String propertyName = evt.getPropertyName();
                if ("started".equals(propertyName)) {
                    if (!busyIconTimer.isRunning()) {
                        statusAnimationLabel.setIcon(busyIcons[0]);
                        busyIconIndex = 0;
                        busyIconTimer.start();
                    }
                    progressBar.setVisible(true);
                    progressBar.setIndeterminate(true);
                } else if ("done".equals(propertyName)) {
                    busyIconTimer.stop();
                    statusAnimationLabel.setIcon(idleIcon);
                    progressBar.setVisible(false);
                    progressBar.setValue(0);
                } else if ("message".equals(propertyName)) {
                    String text = (String)(evt.getNewValue());
                    statusMessageLabel.setText((text == null) ? "" : text);
                    messageTimer.restart();
                } else if ("progress".equals(propertyName)) {
                    int value = (Integer)(evt.getNewValue());
                    progressBar.setVisible(true);
                    progressBar.setIndeterminate(false);
                    progressBar.setValue(value);
                }
            }
        });
        mainPanel.setVisible(true);
    }
    
    

    @Action
    public void showAboutBox() {
        if (aboutBox == null) {
            JFrame mainFrame = DepInheritApp.getApplication().getMainFrame();
            aboutBox = new DepInheritAboutBox(mainFrame);
            aboutBox.setLocationRelativeTo(mainFrame);
        }
        DepInheritApp.getApplication().show(aboutBox);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        lbl_assetName = new javax.swing.JLabel();
        lbl_cost = new javax.swing.JLabel();
        lbl_salvageValue = new javax.swing.JLabel();
        lbl_life = new javax.swing.JLabel();
        txt_assetName = new javax.swing.JTextField();
        txt_cost = new javax.swing.JTextField();
        txt_salvageValue = new javax.swing.JTextField();
        txt_life = new javax.swing.JTextField();
        lbl_selectMethod = new javax.swing.JLabel();
        rdo_straightLine = new javax.swing.JRadioButton();
        rdo_doubleDeclining = new javax.swing.JRadioButton();
        btn_calculate = new javax.swing.JButton();
        btn_clear = new javax.swing.JButton();
        pnl_schedule = new javax.swing.JScrollPane();
        tbl_schedule = new javax.swing.JTable();
        menuBar = new javax.swing.JMenuBar();
        javax.swing.JMenu fileMenu = new javax.swing.JMenu();
        javax.swing.JMenuItem exitMenuItem = new javax.swing.JMenuItem();
        jMenuSave = new javax.swing.JMenuItem();
        javax.swing.JMenu helpMenu = new javax.swing.JMenu();
        javax.swing.JMenuItem aboutMenuItem = new javax.swing.JMenuItem();
        statusPanel = new javax.swing.JPanel();
        javax.swing.JSeparator statusPanelSeparator = new javax.swing.JSeparator();
        statusMessageLabel = new javax.swing.JLabel();
        statusAnimationLabel = new javax.swing.JLabel();
        progressBar = new javax.swing.JProgressBar();
        methodGroup = new javax.swing.ButtonGroup();

        mainPanel.setName("mainPanel"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(depinherit.DepInheritApp.class).getContext().getResourceMap(DepInheritView.class);
        lbl_assetName.setText(resourceMap.getString("lbl_assetName.text")); // NOI18N
        lbl_assetName.setName("lbl_assetName"); // NOI18N

        lbl_cost.setText(resourceMap.getString("lbl_cost.text")); // NOI18N
        lbl_cost.setName("lbl_cost"); // NOI18N

        lbl_salvageValue.setText(resourceMap.getString("lbl_salvageValue.text")); // NOI18N
        lbl_salvageValue.setName("lbl_salvageValue"); // NOI18N

        lbl_life.setText(resourceMap.getString("lbl_life.text")); // NOI18N
        lbl_life.setName("lbl_life"); // NOI18N

        txt_assetName.setText(resourceMap.getString("txt_assetName.text")); // NOI18N
        txt_assetName.setName("txt_assetName"); // NOI18N
        txt_assetName.setPreferredSize(new java.awt.Dimension(60, 20));

        txt_cost.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_cost.setText(resourceMap.getString("txt_Cost.text")); // NOI18N
        txt_cost.setToolTipText(resourceMap.getString("txt_Cost.toolTipText")); // NOI18N
        txt_cost.setName("txt_Cost"); // NOI18N
        txt_cost.setPreferredSize(new java.awt.Dimension(60, 20));

        txt_salvageValue.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_salvageValue.setText(resourceMap.getString("txt_salvageValue.text")); // NOI18N
        txt_salvageValue.setName("txt_salvageValue"); // NOI18N
        txt_salvageValue.setPreferredSize(new java.awt.Dimension(60, 20));
        txt_salvageValue.setRequestFocusEnabled(false);

        txt_life.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_life.setText(resourceMap.getString("txt_life.text")); // NOI18N
        txt_life.setName("txt_life"); // NOI18N
        txt_life.setPreferredSize(new java.awt.Dimension(60, 20));
        txt_life.setRequestFocusEnabled(false);

        lbl_selectMethod.setForeground(resourceMap.getColor("lbl_selectMethod.foreground")); // NOI18N
        lbl_selectMethod.setText(resourceMap.getString("lbl_selectMethod.text")); // NOI18N
        lbl_selectMethod.setName("lbl_selectMethod"); // NOI18N

        methodGroup.add(rdo_straightLine);
        rdo_straightLine.setText(resourceMap.getString("rdo_straightLine.text")); // NOI18N
        rdo_straightLine.setName("rdo_straightLine"); // NOI18N

        methodGroup.add(rdo_doubleDeclining);
        rdo_doubleDeclining.setText(resourceMap.getString("rdo_doubleDeclining.text")); // NOI18N
        rdo_doubleDeclining.setName("rdo_doubleDeclining"); // NOI18N

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(depinherit.DepInheritApp.class).getContext().getActionMap(DepInheritView.class, this);
        btn_calculate.setAction(actionMap.get("calculateDepreciation")); // NOI18N
        btn_calculate.setText(resourceMap.getString("btn_calculate.text")); // NOI18N
        btn_calculate.setName("btn_calculate"); // NOI18N
        btn_calculate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_calculateActionPerformed(evt);
            }
        });

        btn_clear.setText(resourceMap.getString("btn_clear.text")); // NOI18N
        btn_clear.setName("btn_clear"); // NOI18N
        btn_clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_clearActionPerformed(evt);
            }
        });

        pnl_schedule.setName("pnl_schedule"); // NOI18N

        tbl_schedule.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tbl_schedule.setName("tbl_schedule"); // NOI18N
        pnl_schedule.setViewportView(tbl_schedule);

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_assetName)
                    .addComponent(txt_assetName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(60, 60, 60)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_cost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_cost))
                        .addGap(49, 49, 49)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addComponent(lbl_salvageValue)
                                .addGap(66, 66, 66)
                                .addComponent(lbl_life))
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(rdo_doubleDeclining)
                                    .addGroup(mainPanelLayout.createSequentialGroup()
                                        .addComponent(txt_salvageValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(73, 73, 73)
                                        .addComponent(txt_life, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(mainPanelLayout.createSequentialGroup()
                                        .addComponent(btn_calculate)
                                        .addGap(133, 133, 133)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btn_clear))))
                    .addComponent(rdo_straightLine)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                        .addComponent(lbl_selectMethod)
                        .addGap(224, 224, 224)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnl_schedule, javax.swing.GroupLayout.PREFERRED_SIZE, 631, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_assetName)
                    .addComponent(lbl_cost)
                    .addComponent(lbl_salvageValue)
                    .addComponent(lbl_life))
                .addGap(18, 18, 18)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_assetName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_cost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_salvageValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_life, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_clear))
                .addGap(54, 54, 54)
                .addComponent(lbl_selectMethod)
                .addGap(39, 39, 39)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdo_straightLine)
                    .addComponent(rdo_doubleDeclining))
                .addGap(18, 18, 18)
                .addComponent(btn_calculate)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                .addComponent(pnl_schedule, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );

        menuBar.setName("menuBar"); // NOI18N

        fileMenu.setText(resourceMap.getString("fileMenu.text")); // NOI18N
        fileMenu.setName("fileMenu"); // NOI18N

        exitMenuItem.setAction(actionMap.get("quit")); // NOI18N
        exitMenuItem.setName("exitMenuItem"); // NOI18N
        fileMenu.add(exitMenuItem);

        jMenuSave.setText(resourceMap.getString("jMenuSave.text")); // NOI18N
        jMenuSave.setName("jMenuSave"); // NOI18N
        jMenuSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuSaveActionPerformed(evt);
            }
        });
        fileMenu.add(jMenuSave);

        menuBar.add(fileMenu);

        helpMenu.setText(resourceMap.getString("helpMenu.text")); // NOI18N
        helpMenu.setName("helpMenu"); // NOI18N

        aboutMenuItem.setAction(actionMap.get("showAboutBox")); // NOI18N
        aboutMenuItem.setName("aboutMenuItem"); // NOI18N
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);

        statusPanel.setName("statusPanel"); // NOI18N

        statusPanelSeparator.setName("statusPanelSeparator"); // NOI18N

        statusMessageLabel.setName("statusMessageLabel"); // NOI18N

        statusAnimationLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        statusAnimationLabel.setName("statusAnimationLabel"); // NOI18N

        progressBar.setName("progressBar"); // NOI18N

        javax.swing.GroupLayout statusPanelLayout = new javax.swing.GroupLayout(statusPanel);
        statusPanel.setLayout(statusPanelLayout);
        statusPanelLayout.setHorizontalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(statusPanelSeparator, javax.swing.GroupLayout.DEFAULT_SIZE, 723, Short.MAX_VALUE)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(statusMessageLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(progressBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(statusAnimationLabel)
                .addContainerGap())
        );
        statusPanelLayout.setVerticalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addComponent(statusPanelSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(statusMessageLabel)
                    .addComponent(statusAnimationLabel)
                    .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3))
        );

        setComponent(mainPanel);
        setMenuBar(menuBar);
        setStatusBar(statusPanel);
    }// </editor-fold>//GEN-END:initComponents

     
    
    /**
     * 
     * @param evt 
     *  
     * private String assetName;
       private double assetCost;
       private double salvageValue;
       private int lifeOfItem;
     */
    private void btn_calculateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_calculateActionPerformed
        
        String assetName = txt_assetName.getText();
    //    String method = ""; // local method to select calculation method
        
        double assetCost;
        double salvageValue;
        int lifeOfItem;
        
        try {            
            assetCost = Double.parseDouble(txt_cost.getText());
        } catch (NumberFormatException e) {
            statusMessageLabel.setText("Cost field error: " + e.getMessage());
            txt_cost.requestFocusInWindow();
            return;
        }
        
        try {
            salvageValue = Double.parseDouble(txt_salvageValue.getText());
        } catch (NumberFormatException e) {
            statusMessageLabel.setText("Salvage value field error: " + e.getMessage());
            txt_salvageValue.requestFocusInWindow();
            return;
        }
        
        try {
            lifeOfItem = Integer.parseInt(txt_life.getText());
        } catch (NumberFormatException e) {
            statusMessageLabel.setText("Life of item field error: " + e.getMessage());
            txt_life.requestFocusInWindow();
            return;
        }        
        
        String[][] tableValues; // = new String[asset.getLifeOfItem()][4];

        if(rdo_straightLine.isSelected()) {
            assetSL = new AssetSL(assetName, assetCost, salvageValue, lifeOfItem); 
        if(!assetSL.getErrorMessage().isEmpty()) {
            statusMessageLabel.setText(assetSL.getErrorMessage());
            return;
        }
        tableValues = new String[assetSL.getLifeOfItem()][4];
        } else if (rdo_doubleDeclining.isSelected()) {
            // use double declining method
            assetDDL = new AssetDDL(assetName, assetCost, salvageValue, lifeOfItem);
            if(!assetDDL.getErrorMessage().isEmpty()) {
                statusMessageLabel.setText(assetDDL.getErrorMessage());
                return;
            }
            tableValues = new String[assetDDL.getLifeOfItem()][4];
        } else {
            statusMessageLabel.setText("Unknown depreciation type.");
            return;
        }
        
        // asset = new Asset(assetName, assetCost, salvageValue, lifeOfItem);
        
        // Schedule column names
        String[] columnNames = {"Year", "Beginning Balance", "Annual Depreciation", "Ending Balance"};
        
        DefaultTableModel model = new DefaultTableModel(tableValues, columnNames);
        tbl_schedule.setModel(model);
        
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        for(int year = 1; year <= lifeOfItem; year++) {
            tbl_schedule.setValueAt(year, year - 1, 0);
            if(rdo_straightLine.isSelected()) {
                
                tbl_schedule.setValueAt(currency.format(assetSL.getBeginningBalance(year)), year - 1, 1);
                tbl_schedule.setValueAt(currency.format(assetSL.getAnnualDepreciation()), year - 1, 2);
                tbl_schedule.setValueAt(currency.format(assetSL.getEndingBalance(year)), year - 1, 3); 
            } else if (rdo_doubleDeclining.isSelected()) {
                tbl_schedule.setValueAt(currency.format(assetDDL.getBeginningBalance(year)), year - 1, 1);
                tbl_schedule.setValueAt(currency.format(assetDDL.getAnnualDepreciation(year)), year - 1, 2);
                tbl_schedule.setValueAt(currency.format(assetDDL.getEndingBalance(year)), year - 1, 3);
            }
        }
    }//GEN-LAST:event_btn_calculateActionPerformed

    private void btn_clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_clearActionPerformed
        
        statusMessageLabel.setText("");
        txt_assetName.setText("");
        txt_cost.setText("");
        txt_salvageValue.setText("");
        txt_life.setText("");
        
        tbl_schedule.setModel(new DefaultTableModel());
        methodGroup.clearSelection();                
        txt_assetName.requestFocusInWindow();
    }//GEN-LAST:event_btn_clearActionPerformed

    private void jMenuSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuSaveActionPerformed
        
        statusMessageLabel.setText("");
        
        JFileChooser f = new JFileChooser(".");
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files (*.txt", "txt");
        f.setFileFilter(filter);
        f.setDialogTitle("Save Asset to file");
        JDialog dg = new JDialog();
        int rval = f.showSaveDialog(dg);
        
        if(rval == JFileChooser.CANCEL_OPTION) {
            statusMessageLabel.setText("Save canceled.");            
        } else {
            String path = f.getSelectedFile().getAbsolutePath();
  //          statusMessageLabel.setText(asset.setSave(path));
        }
        
        
        
    }//GEN-LAST:event_jMenuSaveActionPerformed


    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_calculate;
    private javax.swing.JButton btn_clear;
    private javax.swing.JMenuItem jMenuSave;
    private javax.swing.JLabel lbl_assetName;
    private javax.swing.JLabel lbl_cost;
    private javax.swing.JLabel lbl_life;
    private javax.swing.JLabel lbl_salvageValue;
    private javax.swing.JLabel lbl_selectMethod;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.ButtonGroup methodGroup;
    private javax.swing.JScrollPane pnl_schedule;
    private javax.swing.JProgressBar progressBar;
    private javax.swing.JRadioButton rdo_doubleDeclining;
    private javax.swing.JRadioButton rdo_straightLine;
    private javax.swing.JLabel statusAnimationLabel;
    private javax.swing.JLabel statusMessageLabel;
    private javax.swing.JPanel statusPanel;
    private javax.swing.JTable tbl_schedule;
    private javax.swing.JTextField txt_assetName;
    private javax.swing.JTextField txt_cost;
    private javax.swing.JTextField txt_life;
    private javax.swing.JTextField txt_salvageValue;
    // End of variables declaration//GEN-END:variables

    private final Timer messageTimer;
    private final Timer busyIconTimer;
    private final Icon idleIcon;
    private final Icon[] busyIcons = new Icon[15];
    private int busyIconIndex = 0;

    private JDialog aboutBox;
}
