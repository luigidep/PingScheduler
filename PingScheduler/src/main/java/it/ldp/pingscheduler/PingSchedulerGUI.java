/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.ldp.pingscheduler;

import java.awt.Color;
import java.lang.management.ManagementFactory;

import javax.swing.JFrame;
import javax.swing.UIManager;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;


/**
 *
 * @author luigi
 */
public class PingSchedulerGUI extends javax.swing.JFrame {
	final static Logger log = Logger.getLogger(PingSchedulerGUI.class.getName());

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
     * Creates new form CalculatorFX
     */
    public PingSchedulerGUI() {
        initComponents();
        //JFrame.setDefaultLookAndFeelDecorated(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        buttonGroupCancPeriodo = new javax.swing.ButtonGroup();
        buttonGroupCancTipo = new javax.swing.ButtonGroup();
        jPanel2 = new javax.swing.JPanel();
        jButtonStart = new javax.swing.JButton();
        jComboIntervallo = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jButtonStop = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jComboPing = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        jComboLogLevel = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        jComboPeriodo = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jRadioButtonCanc1day = new javax.swing.JRadioButton();
        jRadioButtonCanc7days = new javax.swing.JRadioButton();
        jRadioButtonCanc1month = new javax.swing.JRadioButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jButtonCancella = new javax.swing.JButton();
        jRadioButtonCancTutto = new javax.swing.JRadioButton();
        jRadioButtonCancPingOK = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Ping process"));

        jButtonStart.setText("Start");
        jButtonStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jComboIntervallo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "15", "30", "45", "60" }));
        jComboIntervallo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboIntervalloActionPerformed(evt);
            }
        });
        jComboIntervallo.setSelectedIndex(1);

        jLabel1.setText("Intervallo (min.)");

        jButtonStop.setText("Stop");
        jButtonStop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel4.setText("numero di ping");

        jComboPing.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" }));
        jComboPing.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboPingActionPerformed(evt);
            }
        });
        jComboPing.setSelectedIndex(4);

        jLabel6.setText("livello di log");

        jComboLogLevel.setModel(new javax.swing.DefaultComboBoxModel(new String[] { constant.LOGLEVEL_TUTTO, constant.LOGLEVEL_PINGKO }));
        jComboLogLevel.setSelectedIndex(0);
        jLabel7.setText("periodo di ping");

        jComboPeriodo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { constant.PERIODO_SEMPRE, constant.PERIODO_7_21}));
        jComboPeriodo.setSelectedIndex(0);
        
        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)
                            .addComponent(jButtonStart, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jComboPing, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jComboIntervallo, 0, 74, Short.MAX_VALUE))
                                .addComponent(jComboLogLevel, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jComboPeriodo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jButtonStop, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(47, 47, 47))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboPing, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jComboIntervallo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jComboLogLevel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jComboPeriodo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonStart)
                    .addComponent(jButtonStop))
                .addGap(18, 18, 18)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel3.setText("created by ing. Luigi De Pizzol");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Cancellazione log ping"));

        jLabel5.setText("Cancellazione log antecedenti a:");

        jRadioButtonCanc1day.setText(constant.CANCELLA_1DAY);
        jRadioButtonCanc1day.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });

        jRadioButtonCanc7days.setText(constant.CANCELLA_7DAYS);
        jRadioButtonCanc7days.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });

        jRadioButtonCanc1month.setText(constant.CANCELLA_1MONTH);
        jRadioButtonCanc1month.setSelected(true);
        jRadioButtonCanc1month.setActionCommand("1m");
        jRadioButtonCanc1month.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton3ActionPerformed(evt);
            }
        });
        
        buttonGroupCancPeriodo.add(jRadioButtonCanc1day);
        buttonGroupCancPeriodo.add(jRadioButtonCanc7days);
        buttonGroupCancPeriodo.add(jRadioButtonCanc1month);

        jLabel8.setText("Cancellazione tipo di log:");

        jButtonCancella.setText("Cancella");
        jButtonCancella.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancellaLog(evt);
            }
        });

        jRadioButtonCancTutto.setText(constant.CANCELLA_TUTTO);
        jRadioButtonCancTutto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton4ActionPerformed(evt);
            }
        });

        jRadioButtonCancPingOK.setText(constant.CANCELLA_PINGOK);
        jRadioButtonCancPingOK.setActionCommand(constant.CANCELLA_PINGOK);
        jRadioButtonCancPingOK.setSelected(true);
        jRadioButtonCancPingOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton5ActionPerformed(evt);
            }
        });

        buttonGroupCancTipo.add(jRadioButtonCancTutto);
        buttonGroupCancTipo.add(jRadioButtonCancPingOK);

        
        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel8)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jRadioButtonCanc1day)
                        .addGap(18, 18, 18)
                        .addComponent(jRadioButtonCanc7days)
                        .addGap(18, 18, 18)
                        .addComponent(jRadioButtonCanc1month))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jRadioButtonCancTutto)
                        .addGap(38, 38, 38)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButtonCancella, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jRadioButtonCancPingOK))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(53, 53, 53))
            
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButtonCanc1day)
                    .addComponent(jRadioButtonCanc7days)
                    .addComponent(jRadioButtonCanc1month))
                .addGap(40, 40, 40)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButtonCancTutto)
                    .addComponent(jRadioButtonCancPingOK))
                .addGap(42, 42, 42)
                .addComponent(jButtonCancella)
                //.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getAccessibleContext().setAccessibleName("Ping");

        pack();
    }// </editor-fold>                        

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
        System.out.println("start");
        System.out.println("nameprocess:" + ManagementFactory.getRuntimeMXBean().getName());
        String pid = ManagementFactory.getRuntimeMXBean().getName();
        pid = StringUtils.substringBefore(pid, "@");
        Integer min=new Integer(jComboIntervallo.getSelectedItem().toString());
        
        String nping=jComboPing.getSelectedItem().toString();
        String loglevel=jComboLogLevel.getSelectedItem().toString();
        String periodo=jComboPeriodo.getSelectedItem().toString();
        
        
        PingJobScheduler.dowork(min,nping,loglevel,periodo);
        jLabel2.setText("ping scheduler started pid: " + pid);
    }                                        

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
        System.out.println("stop");
        System.exit(0);
    }                                        

    private void jComboPingActionPerformed(java.awt.event.ActionEvent evt) {                                           
        // TODO add your handling code here:
    }                                          

    private void jComboIntervalloActionPerformed(java.awt.event.ActionEvent evt) {                                                 
        // TODO add your handling code here:
    }                                                

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {                                              
        // TODO add your handling code here:
    	jRadioButtonCanc1day.setActionCommand("1");
    	//jRadioButton2.setSelected(false);
    	//jRadioButton3.setSelected(false);
    	
    }
    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {                                              
        // TODO add your handling code here:
    	jRadioButtonCanc7days.setActionCommand("7");
    	//jRadioButton2.setSelected(false);
    	//jRadioButton3.setSelected(false);
    	
    }  
    private void jRadioButton3ActionPerformed(java.awt.event.ActionEvent evt) {                                              
        // TODO add your handling code here:
    	jRadioButtonCanc1month.setActionCommand("1m");
    	//jRadioButton2.setSelected(false);
    	//jRadioButton3.setSelected(false);
    	
    }  

    private void jButtonCancellaLog(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
    	buttonGroupCancPeriodo.getSelection();
    	//log.info("group selection periodo :"+buttonGroupCancPeriodo.getSelection().getActionCommand());
    	//log.info("group selection tipo log:"+buttonGroupCancTipo.getSelection().getActionCommand());
    	String periodo=buttonGroupCancPeriodo.getSelection().getActionCommand();
    	String tipolog=buttonGroupCancTipo.getSelection().getActionCommand();
    	String rowsDel=utility.concellaLogPing(periodo,tipolog);
    	
    	jLabel9.setText(rowsDel);
    	
    	
    }                                        

    private void jRadioButton4ActionPerformed(java.awt.event.ActionEvent evt) {                                              
        // TODO add your handling code here:
    	jRadioButtonCancTutto.setActionCommand(constant.CANCELLA_TUTTO);
    }                                             

    private void jRadioButton5ActionPerformed(java.awt.event.ActionEvent evt) {                                              
        // TODO add your handling code here:
    	jRadioButtonCancPingOK.setActionCommand(constant.CANCELLA_PINGOK);
    }                                             

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PingSchedulerGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PingSchedulerGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PingSchedulerGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PingSchedulerGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PingSchedulerGUI().setVisible(true);
                JFrame.setDefaultLookAndFeelDecorated(true);
                UIManager.put("JFrame.activeTitleBackground", Color.red);

            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.ButtonGroup buttonGroupCancPeriodo;
    private javax.swing.ButtonGroup buttonGroupCancTipo;
    private javax.swing.JButton jButtonStart;
    private javax.swing.JButton jButtonStop;
    private javax.swing.JButton jButtonCancella;
    @SuppressWarnings("rawtypes")
	private javax.swing.JComboBox jComboLogLevel;
    @SuppressWarnings("rawtypes")
	private javax.swing.JComboBox jComboPeriodo;
    @SuppressWarnings("rawtypes")
	private javax.swing.JComboBox jComboIntervallo;
    @SuppressWarnings("rawtypes")
	private javax.swing.JComboBox jComboPing;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JRadioButton jRadioButtonCanc1day;
    private javax.swing.JRadioButton jRadioButtonCanc7days;
    private javax.swing.JRadioButton jRadioButtonCanc1month;
    private javax.swing.JRadioButton jRadioButtonCancTutto;
    private javax.swing.JRadioButton jRadioButtonCancPingOK;
    // End of variables declaration                   
}
