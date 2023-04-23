
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
import javax.swing.table.TableModel;

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
        showHistoryTable();
        showEmployee();
        showService();
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
    public void showService(){
        try {
             int c = 0;
             pre=con.prepareStatement("select s.service_id, s.service_name, s.service_price, e.name, s.service_status from service s inner join employee e on s.service_nv_id = e.id");
             rs=pre.executeQuery();
             ResultSetMetaData re = rs.getMetaData();
             c = re.getColumnCount();
             DefaultTableModel model = (DefaultTableModel) serviceTable.getModel();
             model.setRowCount(0);
             while(rs.next()){
                 Vector v = new Vector();
                 for(int i = 0;i<c;i++){
                     v.add(rs.getString("service_id"));
                     v.add(rs.getString("service_name"));
                     v.add(rs.getString("service_price"));
                     v.add(rs.getString("name"));
                     v.add(rs.getString("service_status"));
                 }
                 model.addRow(v);
             }
         } catch (SQLException ex) {
             Logger.getLogger(manageRoomFrame.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
    public void showEmployee(){
        try {
             int c = 0;
             pre=con.prepareStatement("select * from employee");
             rs=pre.executeQuery();
             ResultSetMetaData re = rs.getMetaData();
             c = re.getColumnCount();
             DefaultTableModel model = (DefaultTableModel) EmployeeTable.getModel();
             model.setRowCount(0);
             while(rs.next()){
                 Vector v = new Vector();
                 for(int i = 0;i<c;i++){
                     v.add(rs.getString("id"));
                     v.add(rs.getString("name"));
                     v.add(rs.getString("phone"));
                     v.add(rs.getString("salary"));
                     v.add(rs.getString("soCCCD"));
                      v.add(rs.getString("sex"));
                     v.add(rs.getString("chucvu"));
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
             pre=con.prepareStatement("select o.oder_id, c.name, s.service_name from oderservice o inner join customer c on o.booking_room = c.roomnumber inner join service s on o.serviceOrder_id = s.service_id ");
             rs=pre.executeQuery();
             ResultSetMetaData re = rs.getMetaData();
             c = re.getColumnCount();
             DefaultTableModel model = (DefaultTableModel) billTable.getModel();
             model.setRowCount(0);
             while(rs.next()){
                 Vector v = new Vector();
                 for(int i = 0;i<c;i++){
                     v.add(rs.getString("oder_id"));
                     v.add(rs.getString("name"));
                     v.add(rs.getString("service_name"));
                 }
                 model.addRow(v);
             }
         } catch (SQLException ex) {
             Logger.getLogger(manageRoomFrame.class.getName()).log(Level.SEVERE, null, ex);
         }
    
    }
    
    public void showHistoryTable(){
        try {
             int c = 0;
             pre=con.prepareStatement("select id, r.roomnumber, r.roomtype,c.phone, c.cccd, c.name, c.amount,c.date,c.outdate, c.day, r.price from bill b inner join customer c on b.customerCCCD = c.cccd inner join room r on b.roomId = r.roomnumber");
             rs=pre.executeQuery();
             ResultSetMetaData re = rs.getMetaData();
             c = re.getColumnCount();
             DefaultTableModel model = (DefaultTableModel) HistoryTable.getModel();
             model.setRowCount(0);
             while(rs.next()){
                 Vector v = new Vector();
                 for(int i = 0;i<c;i++){
                     v.add(rs.getInt("id"));
                     v.add(rs.getString("name"));
                     v.add(rs.getString("cccd"));
                     v.add(rs.getString("phone"));
                     v.add(rs.getString("roomnumber"));
                     v.add(rs.getString("roomtype"));
                     v.add(rs.getString("price"));
                     v.add(String.valueOf(rs.getString("date")));
                     v.add(String.valueOf(rs.getString("outdate")));
                     v.add(rs.getString("day"));
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
        bill_lb = new javax.swing.JLabel();
        checkOut_lb = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
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
        cStatus = new javax.swing.JTextField();
        checkRoom_btn = new javax.swing.JButton();
        BillPanel = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        billTable = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        oderIdTxt = new javax.swing.JTextField();
        bookingRoomTxt = new javax.swing.JTextField();
        serviceNameTxt = new javax.swing.JTextField();
        oderAdd = new javax.swing.JButton();
        updateOder = new javax.swing.JButton();
        deleteOrder = new javax.swing.JButton();
        OderClearForn = new javax.swing.JButton();
        jLabel26 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
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
        jLabel39 = new javax.swing.JLabel();
        billId = new javax.swing.JTextField();
        History = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        HistoryTable = new javax.swing.JTable();
        search = new javax.swing.JButton();
        erefresh_btn1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        searchCCCDTxt = new javax.swing.JTextField();
        HistoryClear = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        esearch = new javax.swing.JButton();
        Employee = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        EmployeeTable = new javax.swing.JTable();
        nvEdit = new javax.swing.JButton();
        nv_addBtn = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        nvIdTxt = new javax.swing.JTextField();
        nvNameTxt = new javax.swing.JTextField();
        nvPhoneTxt = new javax.swing.JTextField();
        nvCCCD = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        nvChucVuTxt = new javax.swing.JTextField();
        sexTxt = new javax.swing.JTextField();
        nvDelete_btn = new javax.swing.JButton();
        nvClearForm = new javax.swing.JButton();
        jLabel37 = new javax.swing.JLabel();
        salaryTxt = new javax.swing.JTextField();
        jLabel42 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        ServicePanel = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        serviceTable = new javax.swing.JTable();
        service_update_btn = new javax.swing.JButton();
        service_add_btn = new javax.swing.JButton();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        service_id = new javax.swing.JTextField();
        service_price = new javax.swing.JTextField();
        service_name = new javax.swing.JTextField();
        service_nvTxt = new javax.swing.JTextField();
        serviceStatus = new javax.swing.JTextField();
        delete_service = new javax.swing.JButton();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        menuPanel.setPreferredSize(new java.awt.Dimension(1000, 100));
        menuPanel.setLayout(new java.awt.GridLayout());

        roomManager_lb.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        roomManager_lb.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/manage room.png"))); // NOI18N
        roomManager_lb.setText("Room");
        roomManager_lb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                roomManager_lbMouseClicked(evt);
            }
        });
        menuPanel.add(roomManager_lb);

        checkIn_lb.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        checkIn_lb.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Customer Registration & Check IN.png"))); // NOI18N
        checkIn_lb.setText("Check In");
        checkIn_lb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                checkIn_lbMouseClicked(evt);
            }
        });
        menuPanel.add(checkIn_lb);

        bill_lb.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        bill_lb.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/bill.jpg"))); // NOI18N
        bill_lb.setText("Order");
        bill_lb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bill_lbMouseClicked(evt);
            }
        });
        menuPanel.add(bill_lb);

        checkOut_lb.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        checkOut_lb.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Customer Check Out.png"))); // NOI18N
        checkOut_lb.setText("Check Out");
        checkOut_lb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                checkOut_lbMouseClicked(evt);
            }
        });
        menuPanel.add(checkOut_lb);

        jLabel34.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel34.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/transaction-history.png"))); // NOI18N
        jLabel34.setText("History");
        jLabel34.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel34MouseClicked(evt);
            }
        });
        menuPanel.add(jLabel34);

        jLabel35.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel35.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/enploy.png"))); // NOI18N
        jLabel35.setText("Employee");
        jLabel35.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel35MouseClicked(evt);
            }
        });
        menuPanel.add(jLabel35);

        jLabel36.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel36.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/customer-service-agent.png"))); // NOI18N
        jLabel36.setText("Service");
        jLabel36.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel36MouseClicked(evt);
            }
        });
        menuPanel.add(jLabel36);

        jLabel19.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/log out.jpg"))); // NOI18N
        jLabel19.setText("Logout");
        jLabel19.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel19MouseClicked(evt);
            }
        });
        menuPanel.add(jLabel19);

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
        cprice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cpriceActionPerformed(evt);
            }
        });

        cphonetxt.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N

        cccdTxt.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N

        croomNumberTxt.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N

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
                            .addGroup(checkInPanelLayout.createSequentialGroup()
                                .addGroup(checkInPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(checkInPanelLayout.createSequentialGroup()
                                        .addGap(77, 77, 77)
                                        .addComponent(jLabel2))
                                    .addGroup(checkInPanelLayout.createSequentialGroup()
                                        .addGap(54, 54, 54)
                                        .addComponent(jLabel4))
                                    .addGroup(checkInPanelLayout.createSequentialGroup()
                                        .addGap(197, 197, 197)
                                        .addComponent(jLabel11)))
                                .addGap(58, 58, 58)
                                .addGroup(checkInPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cphonetxt, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
                                    .addComponent(cnameTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
                                    .addComponent(cccdTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
                                    .addComponent(cprice, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)))
                            .addGroup(checkInPanelLayout.createSequentialGroup()
                                .addGap(75, 75, 75)
                                .addGroup(checkInPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(checkRoom_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6))))
                        .addGroup(checkInPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(checkInPanelLayout.createSequentialGroup()
                                .addGap(211, 211, 211)
                                .addGroup(checkInPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel3)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, checkInPanelLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(22, 22, 22)))
                        .addGap(15, 15, 15)
                        .addGroup(checkInPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(checkInPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(croomNumberTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                                .addComponent(cStatus, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE))
                            .addComponent(dateInTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, checkInPanelLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(checkIn_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(144, 144, 144)
                        .addComponent(clear_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(191, 191, 191))
        );
        checkInPanelLayout.setVerticalGroup(
            checkInPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(checkInPanelLayout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addGroup(checkInPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cnameTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(dateInTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addGap(103, 103, 103)
                .addGroup(checkInPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(checkInPanelLayout.createSequentialGroup()
                        .addGroup(checkInPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cccdTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGap(71, 71, 71)
                        .addGroup(checkInPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cprice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11)))
                    .addGroup(checkInPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 166, Short.MAX_VALUE)
                .addGroup(checkInPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(checkIn_btn)
                    .addComponent(clear_btn)
                    .addComponent(checkRoom_btn))
                .addGap(274, 274, 274))
        );

        showPanel.add(checkInPanel, "card3");

        BillPanel.setBackground(new java.awt.Color(255, 255, 255));

        billTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null}
            },
            new String [] {
                "Ma Order", "Phong Order", "Ten Service"
            }
        ));
        billTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                billTableMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(billTable);

        jLabel7.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel7.setText("Ma Order");

        oderIdTxt.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        bookingRoomTxt.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        serviceNameTxt.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        oderAdd.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        oderAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/plus.png"))); // NOI18N
        oderAdd.setText("Them Order");
        oderAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                oderAddActionPerformed(evt);
            }
        });

        updateOder.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        updateOder.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/edit.png"))); // NOI18N
        updateOder.setText("Sua Order");
        updateOder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateOderActionPerformed(evt);
            }
        });

        deleteOrder.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        deleteOrder.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/delete.png"))); // NOI18N
        deleteOrder.setText("Xoa Order");
        deleteOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteOrderActionPerformed(evt);
            }
        });

        OderClearForn.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        OderClearForn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/broom.png"))); // NOI18N
        OderClearForn.setText("Xoa Font");
        OderClearForn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OderClearFornActionPerformed(evt);
            }
        });

        jLabel26.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel26.setText("Phong Order");

        jLabel38.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel38.setText("Ten Service");

        jLabel40.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel40.setText("Thông tin oder khách hàng");

        jLabel41.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        jLabel41.setText("DANH SÁCH DỊCH VỤ");

        javax.swing.GroupLayout BillPanelLayout = new javax.swing.GroupLayout(BillPanel);
        BillPanel.setLayout(BillPanelLayout);
        BillPanelLayout.setHorizontalGroup(
            BillPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BillPanelLayout.createSequentialGroup()
                .addGroup(BillPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(BillPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(BillPanelLayout.createSequentialGroup()
                            .addGap(45, 45, 45)
                            .addGroup(BillPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(BillPanelLayout.createSequentialGroup()
                                    .addGroup(BillPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(deleteOrder, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(oderAdd, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                                    .addGroup(BillPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(updateOder, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(OderClearForn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(BillPanelLayout.createSequentialGroup()
                                    .addComponent(jLabel38)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(serviceNameTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(BillPanelLayout.createSequentialGroup()
                                    .addComponent(jLabel26)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(bookingRoomTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(BillPanelLayout.createSequentialGroup()
                            .addGap(59, 59, 59)
                            .addComponent(jLabel7)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(oderIdTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(BillPanelLayout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 170, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 545, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(72, 72, 72))
            .addGroup(BillPanelLayout.createSequentialGroup()
                .addGap(394, 394, 394)
                .addComponent(jLabel41)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BillPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator1)
                .addContainerGap())
        );
        BillPanelLayout.setVerticalGroup(
            BillPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BillPanelLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel41)
                .addGap(22, 22, 22)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(BillPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(BillPanelLayout.createSequentialGroup()
                        .addComponent(jLabel40)
                        .addGap(55, 55, 55)
                        .addGroup(BillPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(oderIdTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(55, 55, 55)
                        .addGroup(BillPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel26)
                            .addComponent(bookingRoomTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(73, 73, 73)
                        .addGroup(BillPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel38)
                            .addComponent(serviceNameTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(68, 68, 68)
                        .addGroup(BillPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(oderAdd)
                            .addComponent(updateOder))
                        .addGap(52, 52, 52)
                        .addGroup(BillPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(deleteOrder)
                            .addComponent(OderClearForn)))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 587, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(254, Short.MAX_VALUE))
        );

        showPanel.add(BillPanel, "card5");

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
        dRoomNumberTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dRoomNumberTxtActionPerformed(evt);
            }
        });

        dnameTxt.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        dnameTxt.setEnabled(false);
        dnameTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dnameTxtActionPerformed(evt);
            }
        });

        dphoneTxt.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        dphoneTxt.setEnabled(false);

        dpriceTxt.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        dpriceTxt.setEnabled(false);
        dpriceTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dpriceTxtActionPerformed(evt);
            }
        });

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

        jLabel39.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        jLabel39.setText("Ma Hoa Don");

        billId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                billIdActionPerformed(evt);
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
                        .addGap(31, 31, 31)
                        .addComponent(searchBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)
                        .addComponent(crefresh_btn))
                    .addGroup(checkOutPanelLayout.createSequentialGroup()
                        .addGroup(checkOutPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(dpriceTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17)
                            .addComponent(jLabel12)
                            .addComponent(dnameTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
                            .addComponent(jLabel14)
                            .addComponent(dCheckInTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel39)
                            .addComponent(billId))
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
                .addGroup(checkOutPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(checkOutPanelLayout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(checkOutPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(jLabel13)))
                    .addGroup(checkOutPanelLayout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jLabel39)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(billId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(checkOutPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(checkOutPanelLayout.createSequentialGroup()
                        .addGroup(checkOutPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(dphoneTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dcccdTXt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(38, 38, 38)
                        .addGroup(checkOutPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(jLabel16))
                        .addGap(30, 30, 30)
                        .addGroup(checkOutPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(numberofdayTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(totalAmountTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28))
                    .addGroup(checkOutPanelLayout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(18, 18, 18)
                        .addComponent(dnameTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel14)
                        .addGap(24, 24, 24)
                        .addComponent(dpriceTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(checkOutPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(checkOutPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel18)
                        .addComponent(jButton1)))
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
                .addContainerGap(209, Short.MAX_VALUE))
        );

        showPanel.add(checkOutPanel, "card4");

        History.setBackground(new java.awt.Color(255, 255, 255));

        HistoryTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Hóa Đơn", "Tên Khách Hàng", "Số CCCD", "Số Điện Thoại", "Mã Phòng", "Loại Phòng", "Giá Phòng", "Ngày Đến", "Ngày Đi", "Số ngày ở lại", "Tổng số tiền"
            }
        ));
        jScrollPane4.setViewportView(HistoryTable);

        search.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        search.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/research.png"))); // NOI18N
        search.setText("Tìm Kiếm");
        search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchActionPerformed(evt);
            }
        });

        erefresh_btn1.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        erefresh_btn1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/refresh.png"))); // NOI18N
        erefresh_btn1.setText("Refresh");
        erefresh_btn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                erefresh_btn1ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel1.setText("Tim Kiếm theo CCCD");

        searchCCCDTxt.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        HistoryClear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/broom.png"))); // NOI18N
        HistoryClear.setText("Clear");
        HistoryClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HistoryClearActionPerformed(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        jLabel20.setText("Lịch Sử Thuê Phòng");

        esearch.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        esearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/bill.png"))); // NOI18N
        esearch.setText("Show Bill");
        esearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                esearchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout HistoryLayout = new javax.swing.GroupLayout(History);
        History.setLayout(HistoryLayout);
        HistoryLayout.setHorizontalGroup(
            HistoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, HistoryLayout.createSequentialGroup()
                .addGap(0, 25, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 1159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
            .addGroup(HistoryLayout.createSequentialGroup()
                .addGroup(HistoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(HistoryLayout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addGroup(HistoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(HistoryLayout.createSequentialGroup()
                                .addComponent(esearch, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(106, 106, 106)
                                .addComponent(erefresh_btn1)
                                .addGap(128, 128, 128)
                                .addComponent(HistoryClear))
                            .addGroup(HistoryLayout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(34, 34, 34)
                                .addComponent(searchCCCDTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(65, 65, 65)
                                .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(HistoryLayout.createSequentialGroup()
                        .addGap(404, 404, 404)
                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        HistoryLayout.setVerticalGroup(
            HistoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, HistoryLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel20)
                .addGap(35, 35, 35)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 587, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addGroup(HistoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(search)
                    .addComponent(jLabel1)
                    .addComponent(searchCCCDTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(HistoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(esearch)
                    .addComponent(erefresh_btn1)
                    .addComponent(HistoryClear))
                .addContainerGap(125, Short.MAX_VALUE))
        );

        showPanel.add(History, "card5");

        Employee.setBackground(new java.awt.Color(255, 255, 255));

        EmployeeTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã Nhân Viên", "Tên Nhân Viên", "Số Điện Thoại", "Luong", "Số CCCD", "Giới Tính", "Chức Vụ"
            }
        ));
        EmployeeTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                EmployeeTableMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(EmployeeTable);

        nvEdit.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        nvEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/edit.png"))); // NOI18N
        nvEdit.setText("Sửa");
        nvEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nvEditActionPerformed(evt);
            }
        });

        nv_addBtn.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        nv_addBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/plus.png"))); // NOI18N
        nv_addBtn.setText("Thêm ");
        nv_addBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nv_addBtnActionPerformed(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        jLabel21.setText("Thông tin nhân viên");

        jLabel22.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel22.setText("Mã Nhân Viên");

        jLabel23.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel23.setText("Tên Nhân Viên");

        jLabel24.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel24.setText("Số Điện Thoại");

        jLabel25.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel25.setText("Số CCCD");

        nvIdTxt.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        nvIdTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nvIdTxtActionPerformed(evt);
            }
        });

        nvNameTxt.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        nvPhoneTxt.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        nvCCCD.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        jLabel27.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel27.setText("Giới Tính");

        jLabel28.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel28.setText("Chức Vụ");

        nvChucVuTxt.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        sexTxt.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        nvDelete_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/delete.png"))); // NOI18N
        nvDelete_btn.setText("Xóa");
        nvDelete_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nvDelete_btnActionPerformed(evt);
            }
        });

        nvClearForm.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/broom.png"))); // NOI18N
        nvClearForm.setText("Clear");
        nvClearForm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nvClearFormActionPerformed(evt);
            }
        });

        jLabel37.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel37.setText("Luong");

        salaryTxt.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        jLabel42.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        jLabel42.setText("DANH SÁCH NHÂN VIÊN");

        javax.swing.GroupLayout EmployeeLayout = new javax.swing.GroupLayout(Employee);
        Employee.setLayout(EmployeeLayout);
        EmployeeLayout.setHorizontalGroup(
            EmployeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(EmployeeLayout.createSequentialGroup()
                .addGroup(EmployeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(EmployeeLayout.createSequentialGroup()
                        .addGroup(EmployeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(EmployeeLayout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addGroup(EmployeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel21)
                                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 1159, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(EmployeeLayout.createSequentialGroup()
                                .addGroup(EmployeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(EmployeeLayout.createSequentialGroup()
                                        .addGap(39, 39, 39)
                                        .addGroup(EmployeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel23)
                                            .addComponent(jLabel24)
                                            .addComponent(jLabel22)))
                                    .addGroup(EmployeeLayout.createSequentialGroup()
                                        .addGap(59, 59, 59)
                                        .addComponent(jLabel37)))
                                .addGap(33, 33, 33)
                                .addGroup(EmployeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(EmployeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(salaryTxt)
                                        .addComponent(nvNameTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
                                        .addComponent(nvPhoneTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE))
                                    .addComponent(nvIdTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(81, 81, 81)
                                .addGroup(EmployeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel25)
                                    .addGroup(EmployeeLayout.createSequentialGroup()
                                        .addGroup(EmployeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(EmployeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(nvCCCD, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(nvChucVuTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(EmployeeLayout.createSequentialGroup()
                                                .addComponent(sexTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(91, 91, 91)
                                                .addGroup(EmployeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(nvClearForm, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
                                                    .addGroup(EmployeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(nvDelete_btn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(nvEdit, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
                                                        .addComponent(nv_addBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))))
                            .addGroup(EmployeeLayout.createSequentialGroup()
                                .addGap(458, 458, 458)
                                .addComponent(jLabel42)))
                        .addGap(0, 25, Short.MAX_VALUE))
                    .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        EmployeeLayout.setVerticalGroup(
            EmployeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, EmployeeLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel42)
                .addGap(26, 26, 26)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 498, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(EmployeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(EmployeeLayout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addGroup(EmployeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(nv_addBtn)
                            .addComponent(jLabel22)
                            .addComponent(nvIdTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel27)
                            .addComponent(sexTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(EmployeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel23)
                            .addComponent(nvNameTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nvChucVuTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel28)
                            .addComponent(nvEdit)))
                    .addGroup(EmployeeLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel21)))
                .addGap(30, 30, 30)
                .addGroup(EmployeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(nvPhoneTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nvCCCD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25)
                    .addComponent(nvDelete_btn))
                .addGap(19, 19, 19)
                .addGroup(EmployeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel37)
                    .addComponent(salaryTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nvClearForm))
                .addContainerGap(58, Short.MAX_VALUE))
        );

        showPanel.add(Employee, "card5");

        ServicePanel.setBackground(new java.awt.Color(255, 255, 255));

        serviceTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null}
            },
            new String [] {
                "Mã Dịch Vụ", "Tên Dịch Vụ", "Giá Tiền", "Tên Nhân Viên", "Tình Trạng"
            }
        ));
        serviceTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                serviceTableMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(serviceTable);

        service_update_btn.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        service_update_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/edit.png"))); // NOI18N
        service_update_btn.setText("Chỉnh Sửa");
        service_update_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                service_update_btnActionPerformed(evt);
            }
        });

        service_add_btn.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        service_add_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/plus.png"))); // NOI18N
        service_add_btn.setText("Thêm Dịch Vụ");
        service_add_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                service_add_btnActionPerformed(evt);
            }
        });

        jLabel29.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel29.setText("Mã Dịch Vụ");

        jLabel30.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel30.setText("Tên Dịch Vụ");

        jLabel31.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel31.setText("Giá Tiền");

        jLabel32.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel32.setText("Tên Nhân Viên ");

        jLabel33.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel33.setText("Tình Trạng Dịch Vụ");

        service_id.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        service_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                service_idActionPerformed(evt);
            }
        });

        service_price.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        service_name.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        service_nvTxt.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        service_nvTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                service_nvTxtActionPerformed(evt);
            }
        });

        serviceStatus.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        serviceStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                serviceStatusActionPerformed(evt);
            }
        });

        delete_service.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/delete.png"))); // NOI18N
        delete_service.setText("Xóa Dịch Vụ");
        delete_service.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delete_serviceActionPerformed(evt);
            }
        });

        jLabel43.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        jLabel43.setText("DANH SÁCH DỊCH VỤ");

        jLabel44.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        jLabel44.setText("Thông tin dịch vụ");

        javax.swing.GroupLayout ServicePanelLayout = new javax.swing.GroupLayout(ServicePanel);
        ServicePanel.setLayout(ServicePanelLayout);
        ServicePanelLayout.setHorizontalGroup(
            ServicePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ServicePanelLayout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addGroup(ServicePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel29)
                    .addComponent(jLabel31)
                    .addComponent(jLabel30))
                .addGap(23, 23, 23)
                .addGroup(ServicePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(service_name, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(service_price, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(service_id, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(ServicePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ServicePanelLayout.createSequentialGroup()
                        .addGap(76, 76, 76)
                        .addGroup(ServicePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(ServicePanelLayout.createSequentialGroup()
                                .addComponent(jLabel32)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(ServicePanelLayout.createSequentialGroup()
                                .addComponent(jLabel33)
                                .addGap(45, 558, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ServicePanelLayout.createSequentialGroup()
                        .addGap(221, 221, 221)
                        .addGroup(ServicePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(service_nvTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(serviceStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(ServicePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(delete_service, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(ServicePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(service_add_btn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(service_update_btn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(48, 48, 48))))
            .addGroup(ServicePanelLayout.createSequentialGroup()
                .addGroup(ServicePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ServicePanelLayout.createSequentialGroup()
                        .addGap(466, 466, 466)
                        .addComponent(jLabel43))
                    .addGroup(ServicePanelLayout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(ServicePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel44)
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 1159, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ServicePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator3)
                .addContainerGap())
        );
        ServicePanelLayout.setVerticalGroup(
            ServicePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ServicePanelLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel43)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 496, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel44)
                .addGap(36, 36, 36)
                .addGroup(ServicePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(jLabel32)
                    .addComponent(service_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(service_nvTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(service_add_btn))
                .addGroup(ServicePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ServicePanelLayout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(ServicePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel30)
                            .addComponent(jLabel33)
                            .addComponent(service_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(serviceStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(ServicePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel31)
                            .addComponent(service_price, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(ServicePanelLayout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(service_update_btn)
                        .addGap(30, 30, 30)
                        .addComponent(delete_service)))
                .addContainerGap(96, Short.MAX_VALUE))
        );

        showPanel.add(ServicePanel, "card5");

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
        History.setVisible(false);
        Employee.setVisible(false);
        ServicePanel.setVisible(false);
        showRoomManager();
    }//GEN-LAST:event_roomManager_lbMouseClicked

    private void checkIn_lbMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_checkIn_lbMouseClicked
       roomManage_panel.setVisible(false);
       checkInPanel.setVisible(true);
       checkOutPanel.setVisible(false);
       BillPanel.setVisible(false);
        History.setVisible(false);
        Employee.setVisible(false);
        ServicePanel.setVisible(false);
        
    }//GEN-LAST:event_checkIn_lbMouseClicked

    private void checkOut_lbMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_checkOut_lbMouseClicked
       roomManage_panel.setVisible(false);
       checkInPanel.setVisible(false);
       checkOutPanel.setVisible(true);
       BillPanel.setVisible(false);
        History.setVisible(false);
        Employee.setVisible(false);
        ServicePanel.setVisible(false);
        showCheckOutDate();
    }//GEN-LAST:event_checkOut_lbMouseClicked

    private void bill_lbMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bill_lbMouseClicked
        roomManage_panel.setVisible(false);
        checkInPanel.setVisible(false);
        checkOutPanel.setVisible(false);
        BillPanel.setVisible(true);
         History.setVisible(false);
        Employee.setVisible(false);
        ServicePanel.setVisible(false);
        showBillTable();
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
            showRoomManager();
         
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
                pre = con.prepareStatement("insert into customer(name,phone,date,roomnumber,price,cccd) values(?,?,?,?,?,?)");
                pre.setString(1, cnameTxt.getText());
                pre.setString(2, cphonetxt.getText());
                pre.setString(3, dateInTxt.getText());
                pre.setString(4, croomNumberTxt.getText());
                pre.setString(5, cprice.getText());
                pre.setString(6, cccdTxt.getText());
                pre.executeUpdate();
                PreparedStatement pre1 = con.prepareStatement("update room set status=? where roomnumber=?");
                pre1.setString(1, "booked");
                pre1.setString(2, croomNumberTxt.getText());
                pre1.executeUpdate();
                cphonetxt.setText("");
                cprice.setText("");
                croomNumberTxt.setText("");
                cccdTxt.setText("");
                cnameTxt.setText("");
                cStatus.setText("");
                JOptionPane.showMessageDialog(this, "Check In Success");
               
                
        }   catch (SQLException ex) {
                Logger.getLogger(HomeFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_checkIn_btnActionPerformed

    private void clear_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clear_btnActionPerformed
        cphonetxt.setText("");
        cprice.setText("");
        croomNumberTxt.setText("");
        cccdTxt.setText("");
        cnameTxt.setText("");
        cStatus.setText("");
    }//GEN-LAST:event_clear_btnActionPerformed

    private void checkRoom_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkRoom_btnActionPerformed
        try {
            
            pre = con.prepareCall("select roomtype, price from room where roomnumber=? and status=?");
            pre.setString(1, croomNumberTxt.getText());
            pre.setString(2,"not-book");
            rs = pre.executeQuery();
            if(rs.next()){
                cStatus.setText(rs.getString("roomtype"));
                cprice.setText(rs.getString("price"));
                
            }else{
                JOptionPane.showMessageDialog(this, "Room has been booked by some one or your roomnumber is not correct");
            }
        } catch (SQLException ex) {
            Logger.getLogger(HomeFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_checkRoom_btnActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
                try {
                    pre = con.prepareStatement("update customer set outdate=?,amount=?,day=? where cccd=?");
                    pre.setString(1, dCheckOutTxt.getText());
                    pre.setString(2, totalAmountTxt.getText());
                    pre.setString(3, numberofdayTxt.getText());
                    pre.setString(4, dcccdTXt.getText());
                    pre.executeUpdate();
                    PreparedStatement pre1 = con.prepareStatement("update room set status=? where roomnumber=?");
                    pre1.setString(1, "not-book");
                    pre1.setString(2, dRoomNumberTxt.getText());
                    pre1.executeUpdate();
                    
                    PreparedStatement pre2 = con.prepareStatement("insert into bill(id,roomId, customerCCCD) values(?,?,?)");
                    pre2.setString(3, dcccdTXt.getText());
                    pre2.setString(2,dRoomNumberTxt.getText());
                    pre2.setString(1,billId.getText());
                    pre2.executeUpdate();
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
        billId.setText("");
    }//GEN-LAST:event_dClearBtnActionPerformed

    private void searchBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchBtnActionPerformed
                try {
                    pre = con.prepareStatement("select name,phone,date,price,cccd from customer where roomnumber=?");
                    pre.setString(1, dRoomNumberTxt.getText());
                    rs=pre.executeQuery();
                    if(rs.next()){
                        dnameTxt.setText(rs.getString("name"));
                        dphoneTxt.setText(rs.getString("phone"));
                        dpriceTxt.setText(rs.getString("price"));
                        dcccdTXt.setText(rs.getString("cccd"));
                        dCheckInTxt.setText(rs.getString("date"));
                    }
                    String money="";
                    PreparedStatement pre1 = con.prepareStatement("select sum(service_price) as money from oderservice o inner join service s on o.serviceOrder_id = s.service_id where booking_room=? ");
                    pre1.setString(1, dRoomNumberTxt.getText());
                    ResultSet rs1 = pre1.executeQuery();
                    if(rs1.next()){
                        money = rs1.getString("money");
                    }else{
                        money = "0";
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
                    double price = (Double.parseDouble(rs.getString("price")) * days) + Double.parseDouble(money);
                    totalAmountTxt.setText(String.valueOf(price));
                } catch (SQLException ex) {
                    Logger.getLogger(HomeFrame.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParseException ex) {
                    Logger.getLogger(HomeFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
        
    }//GEN-LAST:event_searchBtnActionPerformed

    private void esearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_esearchActionPerformed
        int row = HistoryTable.getSelectedRow();
        String customerName = HistoryTable.getValueAt(row, 1).toString();  
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

    private void searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchActionPerformed
        try {
             int c = 0;
             pre=con.prepareStatement("select id, r.roomnumber, r.roomtype,c.phone, c.cccd, c.name, c.amount,c.date,c.outdate, c.day, r.price from bill b inner join customer c on b.customerCCCD = c.cccd inner join room r on b.roomId = r.roomnumber where c.cccd=?");
             pre.setString(1, searchCCCDTxt.getText());
             rs=pre.executeQuery();
             ResultSetMetaData re = rs.getMetaData();
             c = re.getColumnCount();
             DefaultTableModel model = (DefaultTableModel) HistoryTable.getModel();
             model.setRowCount(0);
             while(rs.next()){
                 Vector v = new Vector();
                 for(int i = 0;i<c;i++){
                     v.add(rs.getInt("id"));
                     v.add(rs.getString("name"));
                     v.add(rs.getString("cccd"));
                     v.add(rs.getString("phone"));
                     v.add(rs.getString("roomnumber"));
                     v.add(rs.getString("roomtype"));
                     v.add(rs.getString("price"));
                     v.add(String.valueOf(rs.getString("date")));
                     v.add(String.valueOf(rs.getString("outdate")));
                     v.add(rs.getString("day"));
                     v.add(rs.getString("amount"));
                 }
                 model.addRow(v);
             }
         } catch (SQLException ex) {
             Logger.getLogger(manageRoomFrame.class.getName()).log(Level.SEVERE, null, ex);
         }
    }//GEN-LAST:event_searchActionPerformed

    private void erefresh_btn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_erefresh_btn1ActionPerformed
        showHistoryTable();
    }//GEN-LAST:event_erefresh_btn1ActionPerformed

    private void nvEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nvEditActionPerformed
       if((nvIdTxt.getText().equals(""))|| (nvNameTxt.getText().equals("")) || (nvPhoneTxt.getText().equals("")) ||
                (salaryTxt.getText().equals("")) || (sexTxt.getText().equals("")) || (nvChucVuTxt.getText().equals(""))
                || (nvCCCD.getText().equals(""))){
            JOptionPane.showMessageDialog(this, "All field value is not null");
        }else{
       try{
           pre=con.prepareStatement("update employee set name=? , phone=? , salary=? , soCCCD=? , sex=? , chucvu=? where id=?");
           pre.setString(1, nvNameTxt.getText());
           pre.setString(2,nvPhoneTxt.getText());
           pre.setDouble(3, Double.parseDouble(salaryTxt.getText()));
           pre.setString(4,nvCCCD.getText());
           pre.setString(5, sexTxt.getText());
           pre.setString(6, nvChucVuTxt.getText());
           pre.setInt(7, Integer.parseInt(nvIdTxt.getText()));
           pre.executeUpdate();
           JOptionPane.showMessageDialog(this, "Mot nhan vien da duoc cap nhat");
           showEmployee();
       }catch(Exception e){
           e.printStackTrace();
       }
       }
    }//GEN-LAST:event_nvEditActionPerformed

    private void nv_addBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nv_addBtnActionPerformed
        if((nvIdTxt.getText().equals(""))|| (nvNameTxt.getText().equals("")) || (nvPhoneTxt.getText().equals("")) ||
                (salaryTxt.getText().equals("")) || (sexTxt.getText().equals("")) || (nvChucVuTxt.getText().equals(""))
                || (nvCCCD.getText().equals(""))){
            JOptionPane.showMessageDialog(this, "All field value is not null");
        }else{
            try {
                pre = con.prepareStatement("insert into employee(id,name,phone,salary,soCCCD,sex,chucvu) values (?,?,?,?,?,?,?)");
                pre.setString(1, nvIdTxt.getText());
                pre.setString(2, nvNameTxt.getText());
                pre.setString(3, nvPhoneTxt.getText());
                pre.setString(4, salaryTxt.getText());
                pre.setString(5, nvCCCD.getText());
                pre.setString(6, sexTxt.getText());
                pre.setString(7, nvChucVuTxt.getText());
                pre.executeUpdate();
                nvIdTxt.setText("");
                nvNameTxt.setText("");
                nvPhoneTxt.setText("");
                salaryTxt.setText("");
                nvCCCD.setText("");
                sexTxt.setText("");
                nvChucVuTxt.setText("");
                JOptionPane.showMessageDialog(this, "Add success");
                
            } catch (SQLException ex) {
                Logger.getLogger(HomeFrame.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(this, "Some thing wrong in here, Please try again");
            }
               
                
        }
    }//GEN-LAST:event_nv_addBtnActionPerformed

    private void service_update_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_service_update_btnActionPerformed
         if((service_id.getText().equals(""))|| (service_nvTxt.getText().equals("")) || (service_name.getText().equals("")) ||
                (serviceStatus.getText().equals("")) || (service_price.getText().equals(""))){
            JOptionPane.showMessageDialog(this, "Cac truong khong duoc de trong");
        }else{
            String id="";
        try{
            pre = con.prepareStatement("select id from employee where name=?");
            pre.setString(1,service_nvTxt.getText());
            rs=pre.executeQuery();
            if(rs.next()){
                id = rs.getString("id");
                PreparedStatement pre1 = con.prepareStatement("update service set service_name=? , service_price=?, service_nv_id=?, service_status=? where service_id=?");
                pre1.setString(1, service_name.getText());
                pre1.setString(2, service_price.getText());
                pre1.setString(3,id);
                pre1.setString(4, serviceStatus.getText());
                pre1.setString(5, service_id.getText());
                pre1.executeUpdate();
                JOptionPane.showMessageDialog(this, "Them service thanh cong");
                showService();
            }else{
                JOptionPane.showMessageDialog(this, "Khong tim thay nhan vien co ten nhu nay");
            }
            
            System.out.println(id);
        }catch (SQLException ex) {
                Logger.getLogger(HomeFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_service_update_btnActionPerformed

    private void service_add_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_service_add_btnActionPerformed
        if((service_id.getText().equals(""))|| (service_nvTxt.getText().equals("")) || (service_name.getText().equals("")) ||
                (serviceStatus.getText().equals("")) || (service_price.getText().equals(""))){
            JOptionPane.showMessageDialog(this, "All field value is not null");
        }else{
            String id="";
            try {
                
                pre = con.prepareStatement("select id from employee where name = ?");
                pre.setString(1, service_nvTxt.getText());
                rs = pre.executeQuery();
                if(rs.next()){
                     id = rs.getString("id");
                }
                
                PreparedStatement pre1 = con.prepareStatement("insert into service(service_id, service_name,service_price,service_nv_id,service_status) values(?,?,?,?,?);");
                pre1.setString(1, service_id.getText());
                pre1.setString(2, service_name.getText());
                pre1.setString(3, service_price.getText());
                pre1.setString(4, id);
                pre1.setString(5, serviceStatus.getText());
                pre1.executeUpdate();
                service_id.setText("");
                service_name.setText("");
                service_nvTxt.setText("");
                service_price.setText("");
                serviceStatus.setText("");
                
                JOptionPane.showMessageDialog(this, "Check In Success");
                showService();
            } catch (SQLException ex) {
                Logger.getLogger(HomeFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
    }//GEN-LAST:event_service_add_btnActionPerformed

    private void service_idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_service_idActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_service_idActionPerformed

    private void service_nvTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_service_nvTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_service_nvTxtActionPerformed

    private void serviceStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_serviceStatusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_serviceStatusActionPerformed

    private void jLabel34MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel34MouseClicked
       roomManage_panel.setVisible(false);
       checkInPanel.setVisible(false);
       checkOutPanel.setVisible(false);
       BillPanel.setVisible(false);
        History.setVisible(true);
        Employee.setVisible(false);
        ServicePanel.setVisible(false);
        showHistoryTable();
    }//GEN-LAST:event_jLabel34MouseClicked

    private void jLabel35MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel35MouseClicked
        roomManage_panel.setVisible(false);
       checkInPanel.setVisible(false);
       checkOutPanel.setVisible(false);
       BillPanel.setVisible(false);
        History.setVisible(false);
        Employee.setVisible(true);
        ServicePanel.setVisible(false);
        showEmployee();
    }//GEN-LAST:event_jLabel35MouseClicked

    private void jLabel36MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel36MouseClicked
         roomManage_panel.setVisible(false);
       checkInPanel.setVisible(false);
       checkOutPanel.setVisible(false);
       BillPanel.setVisible(false);
        History.setVisible(false);
        Employee.setVisible(false);
        ServicePanel.setVisible(true);
        showService();
    }//GEN-LAST:event_jLabel36MouseClicked

    private void HistoryClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HistoryClearActionPerformed
       searchCCCDTxt.setText("");
    }//GEN-LAST:event_HistoryClearActionPerformed

    private void nvIdTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nvIdTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nvIdTxtActionPerformed

    private void nvDelete_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nvDelete_btnActionPerformed
        int row = EmployeeTable.getSelectedRow();
            String Employee_id_delete = EmployeeTable.getValueAt(row, 0).toString();
        int n =JOptionPane.showConfirmDialog(null, "Are your sure to delete this!", "",JOptionPane.YES_NO_OPTION);
            if(n == JOptionPane.YES_OPTION){
            try {
                pre=con.prepareStatement("delete from employee where id=?");
                pre.setString(1, Employee_id_delete);
                pre.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(HomeFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
                 
            }
    }//GEN-LAST:event_nvDelete_btnActionPerformed

    private void nvClearFormActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nvClearFormActionPerformed
        nvCCCD.setText("");
        nvChucVuTxt.setText("");
        nvIdTxt.setText("");
        nvPhoneTxt.setText("");
        nvNameTxt.setText("");
        salaryTxt.setText("");
        sexTxt.setText("");
    }//GEN-LAST:event_nvClearFormActionPerformed

    private void delete_serviceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delete_serviceActionPerformed
        int row = serviceTable.getSelectedRow();
            String serviceId = serviceTable.getValueAt(row, 0).toString();
        int n =JOptionPane.showConfirmDialog(null, "Are your sure to delete this!", "",JOptionPane.YES_NO_OPTION);
            if(n == JOptionPane.YES_OPTION){
            try {
                pre=con.prepareStatement("delete from service where service_id=?");
                pre.setString(1, serviceId);
                pre.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(HomeFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
                 
            }
            showService();
    }//GEN-LAST:event_delete_serviceActionPerformed

    private void serviceTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_serviceTableMouseClicked
        int row = serviceTable.getSelectedRow();
        TableModel model = serviceTable.getModel();
        service_id.setText(model.getValueAt(row, 0).toString());
        service_name.setText(model.getValueAt(row, 1).toString());
        service_price.setText(model.getValueAt(row, 2).toString());
        service_nvTxt.setText(model.getValueAt(row,3).toString());
        serviceStatus.setText(model.getValueAt(row, 4).toString());
    }//GEN-LAST:event_serviceTableMouseClicked

    private void EmployeeTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EmployeeTableMouseClicked
        int row = EmployeeTable.getSelectedRow();
       nvIdTxt.setText( EmployeeTable.getValueAt(row, 0).toString());
       nvNameTxt.setText(EmployeeTable.getValueAt(row, 1).toString());
       nvPhoneTxt.setText( EmployeeTable.getValueAt(row, 2).toString());
       salaryTxt.setText(EmployeeTable.getValueAt(row, 3).toString());
       nvCCCD.setText(EmployeeTable.getValueAt(row, 4).toString());
       sexTxt.setText(EmployeeTable.getValueAt(row, 5).toString());
       nvChucVuTxt.setText(EmployeeTable.getValueAt(row, 6).toString());
    }//GEN-LAST:event_EmployeeTableMouseClicked

    private void cpriceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cpriceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cpriceActionPerformed

    private void dnameTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dnameTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dnameTxtActionPerformed

    private void oderAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_oderAddActionPerformed
        if((oderIdTxt.getText().equals("")) || (bookingRoomTxt.getText().equals("")) || (serviceNameTxt.getText().equals(""))){
            JOptionPane.showMessageDialog(this, "All field is not null");
        }else{
            String ServiceId = "";
            try{
                PreparedStatement pre1 = con.prepareStatement("select service_id from service where service_name = ?");
                pre1.setString(1, serviceNameTxt.getText());
                rs = pre1.executeQuery();
                if(rs.next()){
                    ServiceId = rs.getString("service_id");
                   
               
            }else{
                    JOptionPane.showMessageDialog(this, "Can not find this service Name ");
                }
                 pre = con.prepareStatement("insert into oderservice(oder_id,booking_room,serviceOrder_id) values(?,?,?)");
                pre.setString(1, oderIdTxt.getText());
                pre.setString(2, bookingRoomTxt.getText());
                pre.setString(3, ServiceId);
                pre.executeUpdate();
                JOptionPane.showMessageDialog(this, "Add Order Success");
                showBillTable();
                oderIdTxt.setText("");
                bookingRoomTxt.setText("");
                serviceNameTxt.setText("");
        }   catch (SQLException ex) {
                Logger.getLogger(HomeFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_oderAddActionPerformed

    private void billTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_billTableMouseClicked
        int row = billTable.getSelectedRow();
         TableModel model = billTable.getModel();
        oderIdTxt.setText(model.getValueAt(row, 0).toString());
        bookingRoomTxt.setText(model.getValueAt(row, 1).toString());
        serviceNameTxt.setText(model.getValueAt(row, 2).toString());
        
    }//GEN-LAST:event_billTableMouseClicked

    private void updateOderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateOderActionPerformed
        if((oderIdTxt.getText().equals("")) || (bookingRoomTxt.getText().equals("")) || (serviceNameTxt.getText().equals(""))){
            JOptionPane.showMessageDialog(this, "All field is not null");
        }else{
            String id = "";
            try{
                PreparedStatement pre1 = con.prepareStatement("select service_id from service where service_name = ?");
                pre1.setString(1, serviceNameTxt.getText());
                rs = pre1.executeQuery();
                if(rs.next()){
                    id = rs.getString("service_id");
                    pre = con.prepareStatement("update oderservice set booking_room=? , serviceOrder_id=? where oder_id=?");
                    pre.setString(1, bookingRoomTxt.getText());
                    pre.setString(2, serviceNameTxt.getText());
                    pre.setString(3, oderIdTxt.getText());
                    JOptionPane.showMessageDialog(this, "One oder have been update");
                    showBillTable();
                    oderIdTxt.setText("");
                    bookingRoomTxt.setText("");
                    serviceNameTxt.setText("");
                }else{
                    JOptionPane.showMessageDialog(this, "Some thing wrong in here");
                }
            } catch (SQLException ex) {
                Logger.getLogger(HomeFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_updateOderActionPerformed

    private void deleteOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteOrderActionPerformed
        int row = billTable.getSelectedRow();
            String oderDeleteId = billTable.getValueAt(row, 0).toString();
        int n =JOptionPane.showConfirmDialog(null, "Are your sure to delete this!", "",JOptionPane.YES_NO_OPTION);
            if(n == JOptionPane.YES_OPTION){
            try {
                pre=con.prepareStatement("delete from oderservice where oder_id=?");
                pre.setString(1, oderDeleteId);
                pre.executeUpdate();
                showBillTable();
                JOptionPane.showMessageDialog(this, "One order have been deleted");
            } catch (SQLException ex) {
                Logger.getLogger(HomeFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
                 
            }
    }//GEN-LAST:event_deleteOrderActionPerformed

    private void OderClearFornActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OderClearFornActionPerformed
        oderIdTxt.setText("");
        bookingRoomTxt.setText("");
        serviceNameTxt.setText("");
    }//GEN-LAST:event_OderClearFornActionPerformed

    private void dRoomNumberTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dRoomNumberTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dRoomNumberTxtActionPerformed

    private void dpriceTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dpriceTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dpriceTxtActionPerformed

    private void billIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_billIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_billIdActionPerformed

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
    private javax.swing.JPanel Employee;
    private javax.swing.JTable EmployeeTable;
    private javax.swing.JPanel History;
    private javax.swing.JButton HistoryClear;
    private javax.swing.JTable HistoryTable;
    private javax.swing.JButton OderClearForn;
    private javax.swing.JPanel ServicePanel;
    private javax.swing.JButton add_btn;
    private javax.swing.JTextField billId;
    private javax.swing.JTable billTable;
    private javax.swing.JLabel bill_lb;
    private javax.swing.JTextField bookingRoomTxt;
    private javax.swing.JTextField cStatus;
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
    private javax.swing.JButton deleteOrder;
    private javax.swing.JButton delete_btn;
    private javax.swing.JButton delete_service;
    private javax.swing.JTextField dnameTxt;
    private javax.swing.JTextField dphoneTxt;
    private javax.swing.JTextField dpriceTxt;
    private javax.swing.JButton edit_btn;
    private javax.swing.JButton erefresh_btn1;
    private javax.swing.JButton esearch;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
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
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTable jTable1;
    private javax.swing.JPanel menuPanel;
    private javax.swing.JTextField numberofdayTxt;
    private javax.swing.JTextField nvCCCD;
    private javax.swing.JTextField nvChucVuTxt;
    private javax.swing.JButton nvClearForm;
    private javax.swing.JButton nvDelete_btn;
    private javax.swing.JButton nvEdit;
    private javax.swing.JTextField nvIdTxt;
    private javax.swing.JTextField nvNameTxt;
    private javax.swing.JTextField nvPhoneTxt;
    private javax.swing.JButton nv_addBtn;
    private javax.swing.JButton oderAdd;
    private javax.swing.JTextField oderIdTxt;
    private javax.swing.JButton refreshBtn;
    private javax.swing.JTable roomManageTable;
    private javax.swing.JPanel roomManage_panel;
    private javax.swing.JLabel roomManager_lb;
    private javax.swing.JTextField salaryTxt;
    private javax.swing.JButton search;
    private javax.swing.JButton searchBtn;
    private javax.swing.JTextField searchCCCDTxt;
    private javax.swing.JTextField serviceNameTxt;
    private javax.swing.JTextField serviceStatus;
    private javax.swing.JTable serviceTable;
    private javax.swing.JButton service_add_btn;
    private javax.swing.JTextField service_id;
    private javax.swing.JTextField service_name;
    private javax.swing.JTextField service_nvTxt;
    private javax.swing.JTextField service_price;
    private javax.swing.JButton service_update_btn;
    private javax.swing.JTextField sexTxt;
    private javax.swing.JPanel showPanel;
    private javax.swing.JTextField totalAmountTxt;
    private javax.swing.JButton updateOder;
    // End of variables declaration//GEN-END:variables

    
}
