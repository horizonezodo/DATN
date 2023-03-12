
package HomePage;


import LoginFrame.SigInFrame;
import RoomManager.EditRoom;
import RoomManager.addRoom;
import java.beans.Statement;
import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Nguyen Hiep
 */
public class HomeFrame extends javax.swing.JFrame {
            Connection con = null;
            Statement statement = null;
            PreparedStatement pre = null;
            ResultSet rs = null; 
    public HomeFrame() {
        initComponents();
        try {
             Class.forName("com.mysql.cj.jdbc.Driver");
             con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hotels","root","123456789");
         } catch (ClassNotFoundException ex) {
             Logger.getLogger(manageRoomFrame.class.getName()).log(Level.SEVERE, null, ex);
         } catch (SQLException ex) {
             Logger.getLogger(manageRoomFrame.class.getName()).log(Level.SEVERE, null, ex);
         }
        showRoomManager();
        
        SimpleDateFormat obj  = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        dateInTxt.setText(obj.format(date));
        
        SimpleDateFormat obj1  = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = new Date();
        dCheckOutTxt.setText(obj1.format(date1));
        
        showCheckOutDate();
        showBillTable();
    }
    public void  showRoomManager(){
         try {
             int c = 0;
             pre=con.prepareStatement("select * from room");
             rs=pre.executeQuery();
             ResultSetMetaData re = rs.getMetaData();
             c = re.getColumnCount();
             DefaultTableModel model = (DefaultTableModel) roomManageTable.getModel();
             model.setRowCount(0);
             while(rs.next()){
                 Vector v = new Vector();
                 for(int i = 0;i<c;i++){
                     v.add(rs.getInt("roomnumber"));
                     v.add(rs.getString("roomtype"));
                     v.add(rs.getString("bed"));
                     v.add(rs.getDouble("price"));
                     v.add(rs.getString("status"));
                 }
                 model.addRow(v);
             }
         } catch (SQLException ex) {
             Logger.getLogger(manageRoomFrame.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
    public void  showBillTable(){
         try {
             int c = 0;
             pre=con.prepareStatement("select name,amount,cccd,phone,price,date,outdate,day from customer");
             rs=pre.executeQuery();
             ResultSetMetaData re = rs.getMetaData();
             c = re.getColumnCount();
             DefaultTableModel model = (DefaultTableModel) billTable.getModel();
             model.setRowCount(0);
             while(rs.next()){
                 Vector v = new Vector();
                 for(int i = 0;i<c;i++){
                     v.add(rs.getString("name"));
                     v.add(rs.getString("phone"));
                     v.add(rs.getString("cccd"));
                     v.add(String.valueOf(rs.getString("date")));
                     v.add(String.valueOf(rs.getString("outdate")));
                     v.add(rs.getString("day"));
                      v.add(rs.getString("price"));
                     v.add(rs.getString("amount"));
                 }
                 model.addRow(v);
             }
         } catch (SQLException ex) {
             Logger.getLogger(manageRoomFrame.class.getName()).log(Level.SEVERE, null, ex);
         }
    
    }
    public void showCheckOutDate(){
        try {
             int c = 0;
             pre=con.prepareStatement("select name,amount,cccd,phone,price,date,outdate from customer");
             rs=pre.executeQuery();
             ResultSetMetaData re = rs.getMetaData();
             c = re.getColumnCount();
             DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
             model.setRowCount(0);
             while(rs.next()){
                 Vector v = new Vector();
                 for(int i = 0;i<c;i++){
                     v.add(rs.getString("name"));
                     v.add(rs.getString("phone"));
                     v.add(rs.getString("cccd"));
                     v.add(String.valueOf(rs.getString("date")));
                     v.add(String.valueOf(rs.getString("outdate")));
                      v.add(rs.getString("price"));
                     v.add(rs.getString("amount"));
                 }
                 model.addRow(v);
             }
         } catch (SQLException ex) {
             Logger.getLogger(manageRoomFrame.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menuPanel = new javax.swing.JPanel();
        roomManager_lb = new javax.swing.JLabel();
        checkIn_lb = new javax.swing.JLabel();
        checkOut_lb = new javax.swing.JLabel();
        bill_lb = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        showPanel = new javax.swing.JPanel();
        roomManage_panel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        roomManageTable = new javax.swing.JTable();
        add_btn = new javax.swing.JButton();
        edit_btn = new javax.swing.JButton();
        delete_btn = new javax.swing.JButton();
        refreshBtn = new javax.swing.JButton();
        checkInPanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        checkIn_btn = new javax.swing.JButton();
        clear_btn = new javax.swing.JButton();
        cnameTxt = new javax.swing.JTextField();
        dateInTxt = new javax.swing.JTextField();
        cprice = new javax.swing.JTextField();
        cphonetxt = new javax.swing.JTextField();
        cccdTxt = new javax.swing.JTextField();
        croomNumberTxt = new javax.swing.JTextField();
        cbedTxt = new javax.swing.JTextField();
        cStatus = new javax.swing.JTextField();
        checkRoom_btn = new javax.swing.JButton();
        checkOutPanel = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        dClearBtn = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        dRoomNumberTxt = new javax.swing.JTextField();
        dnameTxt = new javax.swing.JTextField();
        dphoneTxt = new javax.swing.JTextField();
        dpriceTxt = new javax.swing.JTextField();
        numberofdayTxt = new javax.swing.JTextField();
        dcccdTXt = new javax.swing.JTextField();
        totalAmountTxt = new javax.swing.JTextField();
        dCheckInTxt = new javax.swing.JTextField();
        dCheckOutTxt = new javax.swing.JTextField();
        searchBtn = new javax.swing.JButton();
        crefresh_btn = new javax.swing.JButton();
        BillPanel = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        billTable = new javax.swing.JTable();
        esearch = new javax.swing.JButton();
        erefresh_btn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        menuPanel.setPreferredSize(new java.awt.Dimension(1000, 100));

        roomManager_lb.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        roomManager_lb.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/manage room.png"))); // NOI18N
        roomManager_lb.setText("Room Manager");
        roomManager_lb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                roomManager_lbMouseClicked(evt);
            }
        });

        checkIn_lb.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        checkIn_lb.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Customer Registration & Check IN.png"))); // NOI18N
        checkIn_lb.setText("Customer Check In");
        checkIn_lb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                checkIn_lbMouseClicked(evt);
            }
        });

        checkOut_lb.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        checkOut_lb.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Customer Check Out.png"))); // NOI18N
        checkOut_lb.setText("Customer Check Out");
        checkOut_lb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                checkOut_lbMouseClicked(evt);
            }
        });

        bill_lb.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        bill_lb.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/bill.jpg"))); // NOI18N
        bill_lb.setText("Bill");
        bill_lb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bill_lbMouseClicked(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/log out.jpg"))); // NOI18N
        jLabel19.setText("Logout");
        jLabel19.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel19MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout menuPanelLayout = new javax.swing.GroupLayout(menuPanel);
        menuPanel.setLayout(menuPanelLayout);
        menuPanelLayout.setHorizontalGroup(
            menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuPanelLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(roomManager_lb, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(checkIn_lb, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(checkOut_lb, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(bill_lb, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(jLabel19)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        menuPanelLayout.setVerticalGroup(
            menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuPanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(roomManager_lb)
                    .addComponent(checkIn_lb)
                    .addComponent(checkOut_lb)
                    .addComponent(bill_lb)
                    .addComponent(jLabel19))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        showPanel.setBackground(new java.awt.Color(255, 255, 255));
        showPanel.setPreferredSize(new java.awt.Dimension(1205, 730));
        showPanel.setLayout(new java.awt.CardLayout());

        roomManage_panel.setBackground(new java.awt.Color(255, 255, 255));

        roomManageTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null}
            },
            new String [] {
                "Number", "Type", "Bed", "Price", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(roomManageTable);

        add_btn.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        add_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/plus.png"))); // NOI18N
        add_btn.setText("Add");
        add_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_btnActionPerformed(evt);
            }
        });

        edit_btn.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        edit_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/edit.png"))); // NOI18N
        edit_btn.setText("Edit");
        edit_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edit_btnActionPerformed(evt);
            }
        });

        delete_btn.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        delete_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/delete.png"))); // NOI18N
        delete_btn.setText("Delete");
        delete_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delete_btnActionPerformed(evt);
            }
        });

        refreshBtn.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        refreshBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/refresh.png"))); // NOI18N
        refreshBtn.setText("ReFresh");
        refreshBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout roomManage_panelLayout = new javax.swing.GroupLayout(roomManage_panel);
        roomManage_panel.setLayout(roomManage_panelLayout);
        roomManage_panelLayout.setHorizontalGroup(
            roomManage_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roomManage_panelLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 965, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51)
                .addGroup(roomManage_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(refreshBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(delete_btn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(edit_btn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(add_btn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(26, 26, 26))
        );
        roomManage_panelLayout.setVerticalGroup(
            roomManage_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roomManage_panelLayout.createSequentialGroup()
                .addGroup(roomManage_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roomManage_panelLayout.createSequentialGroup()
                        .addGap(179, 179, 179)
                        .addComponent(add_btn)
                        .addGap(68, 68, 68)
                        .addComponent(edit_btn)
                        .addGap(55, 55, 55)
                        .addComponent(delete_btn)
                        .addGap(53, 53, 53)
                        .addComponent(refreshBtn))
                    .addGroup(roomManage_panelLayout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 694, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(219, Short.MAX_VALUE))
        );

        showPanel.add(roomManage_panel, "card2");

        checkInPanel.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        jLabel2.setText("Customer Name");

        jLabel3.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        jLabel3.setText("Check In Date");

        jLabel4.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        jLabel4.setText("Customer Phonenumber");

        jLabel5.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        jLabel5.setText("Room Number");

        jLabel6.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        jLabel6.setText("CCCD/CMTND");

        jLabel7.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        jLabel7.setText("Bed Type");

        jLabel9.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        jLabel9.setText("Room Type");

        jLabel11.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        jLabel11.setText("Price");

        checkIn_btn.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        checkIn_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/checked.png"))); // NOI18N
        checkIn_btn.setText("Check In Now");
        checkIn_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkIn_btnActionPerformed(evt);
            }
        });

        clear_btn.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        clear_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/broom.png"))); // NOI18N
        clear_btn.setText("Clear");
        clear_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clear_btnActionPerformed(evt);
            }
        });

        cnameTxt.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N

        dateInTxt.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N

        cprice.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        cprice.setEnabled(false);

        cphonetxt.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N

        cccdTxt.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N

        croomNumberTxt.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N

        cbedTxt.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        cbedTxt.setEnabled(false);

        cStatus.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        cStatus.setEnabled(false);

        checkRoom_btn.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        checkRoom_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/search.png"))); // NOI18N
        checkRoom_btn.setText("Check Room");
        checkRoom_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkRoom_btnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout checkInPanelLayout = new javax.swing.GroupLayout(checkInPanel);
        checkInPanel.setLayout(checkInPanelLayout);
        checkInPanelLayout.setHorizontalGroup(
            checkInPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(checkInPanelLayout.createSequentialGroup()
                .addGroup(checkInPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(checkInPanelLayout.createSequentialGroup()
                        .addGroup(checkInPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, checkInPanelLayout.createSequentialGroup()
                                .addGap(112, 112, 112)
                                .addComponent(jLabel11)
                                .addGap(139, 139, 139))
                            .addGroup(checkInPanelLayout.createSequentialGroup()
                                .addGroup(checkInPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(checkInPanelLayout.createSequentialGroup()
                                        .addGap(77, 77, 77)
                                        .addComponent(jLabel2))
                                    .addGroup(checkInPanelLayout.createSequentialGroup()
                                        .addGap(54, 54, 54)
                                        .addComponent(jLabel4)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(checkInPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cphonetxt, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cnameTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cccdTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cprice, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(checkInPanelLayout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addGroup(checkInPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(checkRoom_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))))
                .addGap(211, 211, 211)
                .addGroup(checkInPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(checkInPanelLayout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(checkInPanelLayout.createSequentialGroup()
                        .addGroup(checkInPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel3)
                            .addGroup(checkInPanelLayout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(27, 27, 27)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(checkInPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dateInTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(checkInPanelLayout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addGroup(checkInPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbedTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(croomNumberTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(191, 191, 191))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, checkInPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(checkIn_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(144, 144, 144)
                .addComponent(clear_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(191, 191, 191))
        );
        checkInPanelLayout.setVerticalGroup(
            checkInPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(checkInPanelLayout.createSequentialGroup()
                .addGroup(checkInPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(checkInPanelLayout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addGroup(checkInPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(cnameTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGroup(checkInPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(checkInPanelLayout.createSequentialGroup()
                                .addGap(94, 94, 94)
                                .addGroup(checkInPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(cphonetxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(checkInPanelLayout.createSequentialGroup()
                                .addGap(106, 106, 106)
                                .addGroup(checkInPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel5)
                                    .addComponent(croomNumberTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(checkInPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(checkInPanelLayout.createSequentialGroup()
                                .addGap(97, 97, 97)
                                .addGroup(checkInPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel7)
                                    .addComponent(cbedTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(146, 146, 146)
                                .addGroup(checkInPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cprice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel11)
                                    .addComponent(cStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9)))
                            .addGroup(checkInPanelLayout.createSequentialGroup()
                                .addGap(103, 103, 103)
                                .addGroup(checkInPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cccdTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6)))))
                    .addGroup(checkInPanelLayout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addComponent(dateInTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 97, Short.MAX_VALUE)
                .addGroup(checkInPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(checkIn_btn)
                    .addComponent(clear_btn)
                    .addComponent(checkRoom_btn))
                .addGap(274, 274, 274))
        );

        showPanel.add(checkInPanel, "card3");

        checkOutPanel.setBackground(new java.awt.Color(255, 255, 255));
        checkOutPanel.setEnabled(false);

        jLabel8.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        jLabel8.setText("Room Number");

        jLabel10.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        jLabel10.setText("Customer Phonenumber");

        jLabel12.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        jLabel12.setText("Customer Name");

        jLabel13.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        jLabel13.setText("CCCD/CMT");

        jLabel14.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        jLabel14.setText("Price A Day");

        jLabel15.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        jLabel15.setText("Number Of Days");

        jLabel16.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        jLabel16.setText("Total Amount");

        jLabel17.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        jLabel17.setText("Day Check In");

        jLabel18.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        jLabel18.setText("Day Check Out");

        jButton1.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/checkout.png"))); // NOI18N
        jButton1.setText("Check Out Now");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        dClearBtn.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        dClearBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/broom.png"))); // NOI18N
        dClearBtn.setText("Clear");
        dClearBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dClearBtnActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Customer Name", "Customer phonenumber", "CCCD/CMT", "Day Check In", "Day Check Out", "Price A Day", "Total Amount"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTable1);

        dRoomNumberTxt.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N

        dnameTxt.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        dnameTxt.setEnabled(false);

        dphoneTxt.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        dphoneTxt.setEnabled(false);

        dpriceTxt.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        dpriceTxt.setEnabled(false);

        numberofdayTxt.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N

        dcccdTXt.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        dcccdTXt.setEnabled(false);

        totalAmountTxt.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N

        dCheckInTxt.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        dCheckInTxt.setEnabled(false);

        dCheckOutTxt.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N

        searchBtn.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        searchBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/research.png"))); // NOI18N
        searchBtn.setText("Search");
        searchBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchBtnActionPerformed(evt);
            }
        });

        crefresh_btn.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        crefresh_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/refresh.png"))); // NOI18N
        crefresh_btn.setText("Refresh");
        crefresh_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                crefresh_btnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout checkOutPanelLayout = new javax.swing.GroupLayout(checkOutPanel);
        checkOutPanel.setLayout(checkOutPanelLayout);
        checkOutPanelLayout.setHorizontalGroup(
            checkOutPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(checkOutPanelLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(checkOutPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(checkOutPanelLayout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(79, 79, 79)
                        .addComponent(dRoomNumberTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(66, 66, 66)
                        .addComponent(searchBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(53, 53, 53)
                        .addComponent(crefresh_btn))
                    .addGroup(checkOutPanelLayout.createSequentialGroup()
                        .addGroup(checkOutPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dpriceTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17)
                            .addComponent(jLabel12)
                            .addComponent(dnameTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14)
                            .addComponent(dCheckInTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(172, 172, 172)
                        .addGroup(checkOutPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(numberofdayTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel18)
                            .addComponent(jLabel10)
                            .addComponent(dphoneTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15)
                            .addComponent(dCheckOutTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(174, 174, 174)
                        .addGroup(checkOutPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dClearBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16)
                            .addComponent(dcccdTXt, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13)
                            .addComponent(totalAmountTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1))))
                .addGap(0, 63, Short.MAX_VALUE))
        );
        checkOutPanelLayout.setVerticalGroup(
            checkOutPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(checkOutPanelLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(checkOutPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(dRoomNumberTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchBtn)
                    .addComponent(crefresh_btn))
                .addGap(36, 36, 36)
                .addGroup(checkOutPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jLabel10)
                    .addComponent(jLabel13))
                .addGap(36, 36, 36)
                .addGroup(checkOutPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dnameTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dphoneTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dcccdTXt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addGroup(checkOutPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jLabel15)
                    .addComponent(jLabel16))
                .addGap(30, 30, 30)
                .addGroup(checkOutPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dpriceTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(numberofdayTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(totalAmountTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(checkOutPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(jLabel18)
                    .addComponent(jButton1))
                .addGroup(checkOutPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(checkOutPanelLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(checkOutPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(dCheckInTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dCheckOutTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(checkOutPanelLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(dClearBtn)))
                .addGap(32, 32, 32)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(220, Short.MAX_VALUE))
        );

        showPanel.add(checkOutPanel, "card4");

        BillPanel.setBackground(new java.awt.Color(255, 255, 255));

        billTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Customer Name", "Customer Phonenumber", "Customer CCCD", "Check In Date", "Check Out Date", "Number A Date", "Price A Day", "Total Amount"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane3.setViewportView(billTable);

        esearch.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        esearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/bill.png"))); // NOI18N
        esearch.setText("Show Bill");
        esearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                esearchActionPerformed(evt);
            }
        });

        erefresh_btn.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        erefresh_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/refresh.png"))); // NOI18N
        erefresh_btn.setText("Refresh");
        erefresh_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                erefresh_btnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout BillPanelLayout = new javax.swing.GroupLayout(BillPanel);
        BillPanel.setLayout(BillPanelLayout);
        BillPanelLayout.setHorizontalGroup(
            BillPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BillPanelLayout.createSequentialGroup()
                .addGroup(BillPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(BillPanelLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 1159, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(BillPanelLayout.createSequentialGroup()
                        .addGap(273, 273, 273)
                        .addComponent(esearch, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(126, 126, 126)
                        .addComponent(erefresh_btn)))
                .addContainerGap(31, Short.MAX_VALUE))
        );
        BillPanelLayout.setVerticalGroup(
            BillPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BillPanelLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 587, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(77, 77, 77)
                .addGroup(BillPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(esearch)
                    .addComponent(erefresh_btn))
                .addContainerGap(206, Short.MAX_VALUE))
        );

        showPanel.add(BillPanel, "card5");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menuPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(showPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(menuPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(showPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 947, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        menuPanel.getAccessibleContext().setAccessibleName("");
        menuPanel.getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void roomManager_lbMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_roomManager_lbMouseClicked
        roomManage_panel.setVisible(true);
        checkInPanel.setVisible(false);
        checkOutPanel.setVisible(false);
        BillPanel.setVisible(false);
    }//GEN-LAST:event_roomManager_lbMouseClicked

    private void checkIn_lbMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_checkIn_lbMouseClicked
       roomManage_panel.setVisible(false);
       checkInPanel.setVisible(true);
       checkOutPanel.setVisible(false);
       BillPanel.setVisible(false);
    }//GEN-LAST:event_checkIn_lbMouseClicked

    private void checkOut_lbMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_checkOut_lbMouseClicked
       roomManage_panel.setVisible(false);
       checkInPanel.setVisible(false);
       checkOutPanel.setVisible(true);
       BillPanel.setVisible(false);
    }//GEN-LAST:event_checkOut_lbMouseClicked

    private void bill_lbMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bill_lbMouseClicked
        roomManage_panel.setVisible(false);
        checkInPanel.setVisible(false);
        checkOutPanel.setVisible(false);
        BillPanel.setVisible(true);
    }//GEN-LAST:event_bill_lbMouseClicked

    private void add_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_btnActionPerformed
        addRoom addRoom = new addRoom();
        addRoom.setVisible(true);
    }//GEN-LAST:event_add_btnActionPerformed

    private void edit_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edit_btnActionPerformed
         int row = roomManageTable.getSelectedRow();
        String roomNumber = roomManageTable.getValueAt(row, 0).toString();
       String roomType = roomManageTable.getValueAt(row, 1).toString();
       String bed = roomManageTable.getValueAt(row, 2).toString();
       String price = roomManageTable.getValueAt(row, 3).toString();
       String status = roomManageTable.getValueAt(row, 4).toString();
        EditRoom edit = new EditRoom(roomNumber,roomType,bed,price,status);
       edit.setVisible(true);
    }//GEN-LAST:event_edit_btnActionPerformed

    private void delete_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delete_btnActionPerformed
        int row = roomManageTable.getSelectedRow();
            String roomNumber = roomManageTable.getValueAt(row, 0).toString();
        int n =JOptionPane.showConfirmDialog(null, "Are your sure to delete this!", "",JOptionPane.YES_NO_OPTION);
            if(n == JOptionPane.YES_OPTION){
            try {
                pre=con.prepareStatement("delete from room where roomnumber=?");
                pre.setString(1, roomNumber);
                pre.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(HomeFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
                 
            }
         
    }//GEN-LAST:event_delete_btnActionPerformed

    private void refreshBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshBtnActionPerformed
       showRoomManager();
    }//GEN-LAST:event_refreshBtnActionPerformed

    private void checkIn_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkIn_btnActionPerformed
        if((cnameTxt.getText().equals(""))|| (cccdTxt.getText().equals("")) || (cphonetxt.getText().equals("")) ||
                croomNumberTxt.getText().equals("")){
            JOptionPane.showMessageDialog(this, "All field value is not null");
        }else{
            try {
                pre = con.prepareStatement("insert into customer(name,phone,bed,date,roomnumber,roomtype,price,cccd)values(?,?,?,?,?,?,?,?)");
                pre.setString(1, cnameTxt.getText());
                pre.setString(2, cphonetxt.getText());
                pre.setString(3, cbedTxt.getText());
                pre.setString(4, dateInTxt.getText());
                pre.setString(5, croomNumberTxt.getText());
                pre.setString(6, cStatus.getText());
                pre.setString(7, cprice.getText());
                pre.setString(8, cccdTxt.getText());
                pre.executeUpdate();
                pre = con.prepareStatement("update room set status=? where roomnumber=?");
                pre.setString(1, "booked");
                pre.setString(2, croomNumberTxt.getText());
                pre.executeUpdate();
                cphonetxt.setText("");
                cprice.setText("");
                croomNumberTxt.setText("");
                cccdTxt.setText("");
                cbedTxt.setText("");
                cnameTxt.setText("");
                cStatus.setText("");
                JOptionPane.showMessageDialog(this, "Check In Success");
                
            } catch (SQLException ex) {
                Logger.getLogger(HomeFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
               
                
        }
    }//GEN-LAST:event_checkIn_btnActionPerformed

    private void clear_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clear_btnActionPerformed
        cphonetxt.setText("");
        cprice.setText("");
        croomNumberTxt.setText("");
        cccdTxt.setText("");
        cbedTxt.setText("");
        cnameTxt.setText("");
        cStatus.setText("");
    }//GEN-LAST:event_clear_btnActionPerformed

    private void checkRoom_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkRoom_btnActionPerformed
        try {
            
            pre = con.prepareCall("select roomtype, bed, price from room where roomnumber=? and status=?");
            pre.setString(1, croomNumberTxt.getText());
            pre.setString(2,"not-book");
            rs = pre.executeQuery();
            if(rs.next()){
                cStatus.setText(rs.getString("roomtype"));
                cprice.setText(rs.getString("price"));
                cbedTxt.setText(rs.getString("bed"));
            }else{
                JOptionPane.showMessageDialog(this, "Room has been booked by some one or your roomnumber is not correct");
            }
        } catch (SQLException ex) {
            Logger.getLogger(HomeFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_checkRoom_btnActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
                try {
                    pre = con.prepareStatement("update customer set outdate=?,amount=?,day=?, status=? where cccd=?");
                    pre.setString(1, dCheckOutTxt.getText());
                    pre.setString(2, totalAmountTxt.getText());
                    pre.setString(3, numberofdayTxt.getText());
                    pre.setString(4, "check-ked");
                    pre.setString(5, dcccdTXt.getText());
                    pre.executeUpdate();
                    pre = con.prepareStatement("update room set status=? where roomnumber=?");
                    pre.setString(1, "not-book");
                    pre.setString(2, dRoomNumberTxt.getText());
                    pre.executeUpdate();
                } catch (SQLException ex) {
                    Logger.getLogger(HomeFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void dClearBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dClearBtnActionPerformed
        dnameTxt.setText("");
        dateInTxt.setText("");
        dphoneTxt.setText("");
        dCheckInTxt.setText("");
        numberofdayTxt.setText("");
        dpriceTxt.setText("");
        dRoomNumberTxt.setText("");
        totalAmountTxt.setText("");
        dcccdTXt.setText("");
    }//GEN-LAST:event_dClearBtnActionPerformed

    private void searchBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchBtnActionPerformed
                try {
                    pre = con.prepareStatement("select name,phone,date,price,cccd from customer where roomnumber=?");
                    pre.setString(1, dRoomNumberTxt.getText());
                    rs=pre.executeQuery();
                    if(rs.next()){
                        dnameTxt.setText(rs.getString("name"));
                        dphoneTxt.setText(rs.getString("phone"));
                        dCheckInTxt.setText(rs.getString("date"));
                        dpriceTxt.setText(rs.getString("price"));
                        dcccdTXt.setText(rs.getString("cccd"));
                    }
                    String s1,s2;
                    s1=dCheckOutTxt.getText();
                    s2=rs.getString("date");
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    Date d1 = format.parse(s1);
                    Date d2 = format.parse(s2);
                    long diff = d1.getTime()-d2.getTime();
                    int days=(int) (diff / (24*60*60*1000));
                    if(days == 0){
                        days=1;
                    }
                    numberofdayTxt.setText(String.valueOf(days));
                    double price = Double.parseDouble(rs.getString("price")) * days;
                    totalAmountTxt.setText(String.valueOf(price));
                } catch (SQLException ex) {
                    Logger.getLogger(HomeFrame.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParseException ex) {
                    Logger.getLogger(HomeFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
        
    }//GEN-LAST:event_searchBtnActionPerformed

    private void esearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_esearchActionPerformed
        int row = billTable.getSelectedRow();
        String customerName = billTable.getValueAt(row, 0).toString();  
        PrintBillFrame bill = new PrintBillFrame(customerName);
        bill.setVisible(true);
               
    }//GEN-LAST:event_esearchActionPerformed

    private void jLabel19MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel19MouseClicked
         int n =JOptionPane.showConfirmDialog(null, "Do you want logout", "",JOptionPane.YES_NO_OPTION);
            if(n == JOptionPane.YES_OPTION){
                SigInFrame sigin = new SigInFrame();
                 this.setVisible(false);
                sigin.setVisible(true);
            }
    }//GEN-LAST:event_jLabel19MouseClicked

    private void crefresh_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_crefresh_btnActionPerformed
        showCheckOutDate();
    }//GEN-LAST:event_crefresh_btnActionPerformed

    private void erefresh_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_erefresh_btnActionPerformed
        showBillTable();
    }//GEN-LAST:event_erefresh_btnActionPerformed

    /**830-200
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
            java.util.logging.Logger.getLogger(HomeFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HomeFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HomeFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HomeFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HomeFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel BillPanel;
    private javax.swing.JButton add_btn;
    private javax.swing.JTable billTable;
    private javax.swing.JLabel bill_lb;
    private javax.swing.JTextField cStatus;
    private javax.swing.JTextField cbedTxt;
    private javax.swing.JTextField cccdTxt;
    private javax.swing.JPanel checkInPanel;
    private javax.swing.JButton checkIn_btn;
    private javax.swing.JLabel checkIn_lb;
    private javax.swing.JPanel checkOutPanel;
    private javax.swing.JLabel checkOut_lb;
    private javax.swing.JButton checkRoom_btn;
    private javax.swing.JButton clear_btn;
    private javax.swing.JTextField cnameTxt;
    private javax.swing.JTextField cphonetxt;
    private javax.swing.JTextField cprice;
    private javax.swing.JButton crefresh_btn;
    private javax.swing.JTextField croomNumberTxt;
    private javax.swing.JTextField dCheckInTxt;
    private javax.swing.JTextField dCheckOutTxt;
    private javax.swing.JButton dClearBtn;
    private javax.swing.JTextField dRoomNumberTxt;
    private javax.swing.JTextField dateInTxt;
    private javax.swing.JTextField dcccdTXt;
    private javax.swing.JButton delete_btn;
    private javax.swing.JTextField dnameTxt;
    private javax.swing.JTextField dphoneTxt;
    private javax.swing.JTextField dpriceTxt;
    private javax.swing.JButton edit_btn;
    private javax.swing.JButton erefresh_btn;
    private javax.swing.JButton esearch;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JPanel menuPanel;
    private javax.swing.JTextField numberofdayTxt;
    private javax.swing.JButton refreshBtn;
    private javax.swing.JTable roomManageTable;
    private javax.swing.JPanel roomManage_panel;
    private javax.swing.JLabel roomManager_lb;
    private javax.swing.JButton searchBtn;
    private javax.swing.JPanel showPanel;
    private javax.swing.JTextField totalAmountTxt;
    // End of variables declaration//GEN-END:variables

    
}
