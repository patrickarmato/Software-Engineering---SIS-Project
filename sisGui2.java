package project;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class sisGui2 extends JFrame {

    private JTextField usernameText;
    private JPasswordField passwordText;
    private JTable AccountTable;
    private JTable personalTable;
    private JTable courseTable;
    private JTable studentTable;
    private JTable table_1;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField textField_3;
    private JTextField textField_4;
    private JTextField textField_5;
    private JTable table_2;
    private JTextField textField_6;
    private JTextField textField_7;
    private JTextField textField_8;
    private JTextField textField_10;
    private JTextField textField_9;
    private JTextField textField_11;
    private JTextField textField_12;
    private JTextField textField_13;
    private JTextField textField_14;
    private JTable table_3;

    public static void main(String[] args) {
        SISConnection con = new SISConnection();
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    sisGui2 frame = new sisGui2();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                
            }
            
        });
        
    }
    private void clearTable(JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0); // Clear all rows from the table model
    }

    public sisGui2() {
        setTitle("Student Information Systems");
        setType(Type.UTILITY);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 779, 439);
        JPanel contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setBounds(-23, -38, 823, 484);
        contentPane.add(tabbedPane);

        JPanel authentification = new JPanel();
        authentification.setBackground(new Color(128, 0, 0));
        tabbedPane.addTab("New tab", null, authentification, null);
        authentification.setLayout(null);

        JLabel loginLabel = new JLabel("Login");
        loginLabel.setForeground(new Color(255, 255, 224));
        loginLabel.setFont(new Font("Krungthep", Font.BOLD | Font.ITALIC, 30));
        loginLabel.setBounds(372, 90, 147, 46);
        authentification.add(loginLabel);

        usernameText = new JTextField();
        usernameText.setFont(new Font("Krungthep", Font.PLAIN, 14));
        usernameText.setBounds(345, 171, 147, 34);
        authentification.add(usernameText);
        usernameText.setColumns(10);

        passwordText = new JPasswordField();
        passwordText.setFont(new Font("Krungthep", Font.PLAIN, 14));
        passwordText.setColumns(10);
        passwordText.setBounds(345, 234, 147, 34);
        authentification.add(passwordText);

        JLabel username = new JLabel("Username:");
        username.setForeground(new Color(255, 255, 224));
        username.setFont(new Font("Krungthep", Font.PLAIN, 14));
        username.setBounds(259, 180, 99, 16);
        authentification.add(username);

        JLabel password = new JLabel("Password: ");
        password.setForeground(new Color(255, 255, 224));
        password.setFont(new Font("Krungthep", Font.PLAIN, 14));
        password.setBounds(262, 243, 111, 16);
        authentification.add(password);

        JButton button = new JButton("Sign In");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //tabbedPane.setSelectedIndex(1);
                try {
                    String username = usernameText.getText();
                    String password = new String(passwordText.getPassword());

                    String sql = "select * from Account where username='" + username + "' and password='" + password + "'";
                    ResultSet rs3 = SISConnection.st.executeQuery(sql);
                    if (rs3.next()) {
                        tabbedPane.setSelectedIndex(1);
                    } else {
                        JOptionPane.showMessageDialog(passwordText, "Name or Password is incorrect", "Error", JOptionPane.ERROR_MESSAGE);
                        usernameText.setText("");
                        passwordText.setText("");
                    }
                } catch (Exception e1) {
                    System.out.println(e1.getMessage());
                }
            }
        });
        button.setFont(new Font("Krungthep", Font.PLAIN, 14));
        button.setBounds(355, 280, 117, 29);
        authentification.add(button);

        JButton btnAdmin = new JButton("Admin Login");
        btnAdmin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tabbedPane.setSelectedIndex(3);
                usernameText.setText("");
                passwordText.setText("");
            }
        });
        btnAdmin.setFont(new Font("Krungthep", Font.PLAIN, 14));
        btnAdmin.setBounds(355, 312, 117, 29);
        authentification.add(btnAdmin);

        JPanel homepage = new JPanel();
        homepage.setLayout(null);
        homepage.setBackground(new Color(128, 0, 0));
        tabbedPane.addTab("New tab", null, homepage, null);

        JLabel SJU = new JLabel("St. John's University");
        SJU.setForeground(new Color(250, 235, 215));
        SJU.setFont(new Font("Krungthep", Font.BOLD | Font.ITALIC, 30));
        SJU.setBounds(233, 88, 341, 46);
        homepage.add(SJU);

        JButton btnAccountInfo = new JButton("Account Info");
        btnAccountInfo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tabbedPane.setSelectedIndex(2);
                clearTable(AccountTable);
                try {
                    String username = usernameText.getText();

                    String sql = "select idStudent, firstName, lastName, email, phoneNumber from Student where idStudent='"+username+"'";
                    ResultSet rs4=SISConnection.st.executeQuery(sql);
                    ResultSetMetaData rsmd=rs4.getMetaData();
                    DefaultTableModel model=(DefaultTableModel) AccountTable.getModel();

                    int cols=rsmd.getColumnCount();
                    String[] colName=new String[cols];
                    for(int i=0;i<cols;i++)
                        colName[i]=rsmd.getColumnName(i+1);
                    model.setColumnIdentifiers(colName);
                    String fname, lname, email, phoneNumber,StudentID;
                    while(rs4.next()) {
                    	StudentID=rs4.getString("idStudent");
                        fname=rs4.getString("firstName");
                        lname=rs4.getString("lastName");
                        email=rs4.getString("email");
                        phoneNumber=rs4.getString("phoneNumber");
                        String[] row= {StudentID,fname,lname,email,phoneNumber};
                        model.addRow(row);
                    }
                } catch (Exception e3) {
                    e3.printStackTrace();
                }
                
            }
            
        });
        btnAccountInfo.setFont(new Font("Krungthep", Font.PLAIN, 18));
        btnAccountInfo.setBounds(83, 219, 156, 79);
        homepage.add(btnAccountInfo);

        JButton btnStudentInfo = new JButton("Student Info");
        btnStudentInfo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tabbedPane.setSelectedIndex(5);
                clearTable(studentTable);
                
                try {
                    String username = usernameText.getText();

                    String sql = "select idStudent, gpa, major from Student where idStudent='"+username+"'";
                    ResultSet rs4=SISConnection.st.executeQuery(sql);
                    ResultSetMetaData rsmd=rs4.getMetaData();
                    DefaultTableModel model=(DefaultTableModel) studentTable.getModel();

                    int cols=rsmd.getColumnCount();
                    String[] colName=new String[cols];
                    for(int i=0;i<cols;i++)
                        colName[i]=rsmd.getColumnName(i+1);
                    model.setColumnIdentifiers(colName);
                    String studentID, gpa, major;
                    while(rs4.next()) {
                        studentID=rs4.getString("idStudent");
                        gpa=rs4.getString("gpa");
                        major=rs4.getString("major");
                        String[] row= {studentID,gpa,major};
                        model.addRow(row);
                    }
                } catch (Exception e3) {
                    e3.printStackTrace();
                }
            }
            
        });
        btnStudentInfo.setFont(new Font("Krungthep", Font.PLAIN, 18));
        btnStudentInfo.setBounds(319, 219, 156, 79);
        homepage.add(btnStudentInfo);

        JButton btnCourseInfo = new JButton("Course Info");
        btnCourseInfo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tabbedPane.setSelectedIndex(6);
                clearTable(courseTable);
                clearTable(table_3);
                try {
                    String username = usernameText.getText();

                    String sql = "SELECT c.idcourses, c.courseName, c.courseDescription, c.courseCredit " +
                            "FROM courses c " +
                            "INNER JOIN enrollments e ON c.idcourses = e.course_id " +
                            "WHERE e.student_id = ?";
               PreparedStatement statement = SISConnection.con.prepareStatement(sql);
               statement.setString(1, username); // Set the parameter for student_id
               ResultSet rs4 = statement.executeQuery();
                    ResultSetMetaData rsmd=rs4.getMetaData();
                    DefaultTableModel model=(DefaultTableModel) courseTable.getModel();

                    int cols=rsmd.getColumnCount();
                    String[] colName=new String[cols];
                    for(int i=0;i<cols;i++)
                        colName[i]=rsmd.getColumnName(i+1);
                    model.setColumnIdentifiers(colName);
                    String courseID, courseName, courseDescription, courseCredit;
                    while(rs4.next()) {
                        courseID=rs4.getString("idcourses");
                        courseName=rs4.getString("courseName");
                        courseDescription=rs4.getString("courseDescription");
                        courseCredit=rs4.getString("courseCredit");
                        String[] row= {courseID,courseName,courseDescription,courseCredit};
                        model.addRow(row);
                    }
                } catch (Exception e3) {
                    e3.printStackTrace();
                }
                try {
                    String username = usernameText.getText();

                    String sql = "SELECT attendance from enrollments where student_id = ? ";                                
               PreparedStatement statement = SISConnection.con.prepareStatement(sql);
               statement.setString(1, username); // Set the parameter for student_id
               ResultSet rs4 = statement.executeQuery();
                    ResultSetMetaData rsmd=rs4.getMetaData();
                    DefaultTableModel model=(DefaultTableModel) table_3.getModel();

                    int cols=rsmd.getColumnCount();
                    String[] colName=new String[cols];
                    for(int i=0;i<cols;i++)
                        colName[i]=rsmd.getColumnName(i+1);
                    model.setColumnIdentifiers(colName);
                    String attendance;
                    while(rs4.next()) {
                        attendance=rs4.getString("attendance");
                        String[] row= {attendance};
                        model.addRow(row);
                    }
                } catch (Exception e3) {
                    e3.printStackTrace();
                }
            }
        });
        btnCourseInfo.setFont(new Font("Krungthep", Font.PLAIN, 18));
        btnCourseInfo.setBounds(549, 219, 156, 79);
        homepage.add(btnCourseInfo);

        JButton btnLogOut = new JButton("Log Out");
        btnLogOut.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tabbedPane.setSelectedIndex(0);
                usernameText.setText("");
                passwordText.setText("");
            }
        });
        btnLogOut.setFont(new Font("Krungthep", Font.PLAIN, 14));
        btnLogOut.setBounds(667, 6, 117, 29);
        homepage.add(btnLogOut);

        JPanel accountInfo = new JPanel();
        accountInfo.setBackground(new Color(128, 0, 0));
        tabbedPane.addTab("New tab", null, accountInfo, null);
        accountInfo.setLayout(null);

        JLabel accountInfoTitle = new JLabel("Account Info");
        accountInfoTitle.setForeground(new Color(255, 255, 255));
        accountInfoTitle.setFont(new Font("Krungthep", Font.BOLD | Font.ITALIC, 30));
        accountInfoTitle.setBounds(26, 17, 341, 46);
        accountInfo.add(accountInfoTitle);

        JButton btnPersonalInfo = new JButton("Personal Info");
        btnPersonalInfo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tabbedPane.setSelectedIndex(4);
                clearTable(personalTable);
                try {
                    String username = usernameText.getText();

                    String sql = "select dateOfBirth, gender, address, city, state, zipCode from Student where idStudent='"+username+"'";
                    ResultSet rs4=SISConnection.st.executeQuery(sql);
                    ResultSetMetaData rsmd=rs4.getMetaData();
                    DefaultTableModel model=(DefaultTableModel) personalTable.getModel();

                    int cols=rsmd.getColumnCount();
                    String[] colName=new String[cols];
                    for(int i=0;i<cols;i++)
                        colName[i]=rsmd.getColumnName(i+1);
                    model.setColumnIdentifiers(colName);
                    String dateOfBirth, gender, address, city, state, zipCode;
                    while(rs4.next()) {
                        dateOfBirth=rs4.getString("dateOfBirth");
                        gender=rs4.getString("gender");
                        address=rs4.getString("address");
                        city=rs4.getString("city");
                        state=rs4.getString("state");
                        zipCode=rs4.getString("zipCode");
                        String[] row= {dateOfBirth,gender,address,city,state,zipCode};
                        model.addRow(row);
                    }
                } catch (Exception e3) {
                    e3.printStackTrace();
                }
            }
        });
        btnPersonalInfo.setFont(new Font("Krungthep", Font.PLAIN, 14));
        btnPersonalInfo.setBounds(36, 70, 128, 29);
        accountInfo.add(btnPersonalInfo);

        JButton btnHome = new JButton("Home");
        btnHome.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tabbedPane.setSelectedIndex(1);
            }
        });
        btnHome.setEnabled(true);
        btnHome.setFont(new Font("Krungthep", Font.PLAIN, 14));
        btnHome.setBounds(667, 6, 117, 29);
        accountInfo.add(btnHome);
        
        AccountTable = new JTable();
        AccountTable.setBounds(46, 131, 712, 126);
        accountInfo.add(AccountTable);
        
        JLabel lblNewLabel = new JLabel("Student ID\r\n");
        lblNewLabel.setForeground(new Color(255, 255, 255));
        lblNewLabel.setBounds(46, 110, 80, 16);
        accountInfo.add(lblNewLabel);
        
        JLabel lblFirstName = new JLabel("First Name\r\n");
        lblFirstName.setForeground(new Color(255, 255, 255));
        lblFirstName.setBounds(167, 110, 80, 16);
        accountInfo.add(lblFirstName);
        
        JLabel lblLastName = new JLabel("Last Name");
        lblLastName.setForeground(new Color(255, 255, 255));
        lblLastName.setBounds(313, 110, 67, 16);
        accountInfo.add(lblLastName);
        
        JLabel lblEmail_2 = new JLabel("Email");
        lblEmail_2.setForeground(new Color(255, 255, 255));
        lblEmail_2.setBounds(475, 110, 86, 16);
        accountInfo.add(lblEmail_2);
        
        JLabel lblEmail_2_1 = new JLabel("Phone Number\r\n");
        lblEmail_2_1.setForeground(new Color(255, 255, 255));
        lblEmail_2_1.setBounds(615, 110, 107, 16);
        accountInfo.add(lblEmail_2_1);
        
        JButton btnNewButton = new JButton("Update");
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		DefaultTableModel model = (DefaultTableModel) AccountTable.getModel();
                int rowCount = model.getRowCount();

                // Update database with the new data
                for (int i = 0; i < rowCount; i++) {
                    String id = (String) model.getValueAt(i, 0); // Assuming the first column contains ID

                }

                JOptionPane.showMessageDialog(null, "Account information updated successfully!");
        	}
        });
        btnNewButton.setBounds(100, 291, 117, 29);
        accountInfo.add(btnNewButton);

        JPanel authentification_1 = new JPanel();
        authentification_1.setLayout(null);
        authentification_1.setBackground(new Color(128, 0, 0));
        tabbedPane.addTab("New tab", null, authentification_1, null);

        JLabel lblAdminLogin = new JLabel("Admin Login");
        lblAdminLogin.setForeground(new Color(255, 255, 255));
        lblAdminLogin.setFont(new Font("Krungthep", Font.BOLD | Font.ITALIC, 30));
        lblAdminLogin.setBounds(338, 93, 243, 46);
        authentification_1.add(lblAdminLogin);

        JTextField textField = new JTextField();
        textField.setFont(new Font("Krungthep", Font.PLAIN, 14));
        textField.setColumns(10);
        textField.setBounds(345, 171, 163, 34);
        authentification_1.add(textField);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setFont(new Font("Krungthep", Font.PLAIN, 14));
        passwordField.setColumns(10);
        passwordField.setBounds(345, 234, 163, 34);
        authentification_1.add(passwordField);

        JLabel username_1 = new JLabel("Username:");
        username_1.setForeground(new Color(255, 255, 255));
        username_1.setFont(new Font("Krungthep", Font.PLAIN, 14));
        username_1.setBounds(259, 180, 99, 16);
        authentification_1.add(username_1);

        JLabel password_1 = new JLabel("Password: ");
        password_1.setForeground(new Color(255, 255, 255));
        password_1.setFont(new Font("Krungthep", Font.PLAIN, 14));
        password_1.setBounds(262, 243, 111, 16);
        authentification_1.add(password_1);

        JButton button_1 = new JButton("Sign In");
        button_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String username = textField.getText();
                    String password = passwordField.getText();

                    String sql = "select * from Admin where username='" + username + "' and password='" + password + "'";
                    ResultSet rs3 = SISConnection.st.executeQuery(sql);
                    if (rs3.next()) {
                        tabbedPane.setSelectedIndex(7);
                    } else {
                        JOptionPane.showMessageDialog(passwordText, "Name or Password is incorrect", "Error", JOptionPane.ERROR_MESSAGE);
                        usernameText.setText("");
                        passwordText.setText("");
                    }
                } catch (Exception e1) {
                    System.out.println(e1.getMessage());
                }
            }
        });
        button_1.setFont(new Font("Krungthep", Font.PLAIN, 14));
        button_1.setBounds(355, 279, 127, 29);
        authentification_1.add(button_1);

        JButton btnAdmin_1 = new JButton("Student Login");
        btnAdmin_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tabbedPane.setSelectedIndex(0);
                usernameText.setText("");
                passwordText.setText("");
            }
        });
        btnAdmin_1.setFont(new Font("Krungthep", Font.PLAIN, 14));
        btnAdmin_1.setBounds(355, 312, 127, 29);
        authentification_1.add(btnAdmin_1);

        JPanel personalInfo = new JPanel();
        personalInfo.setLayout(null);
        personalInfo.setBackground(new Color(128, 0, 0));
        tabbedPane.addTab("New tab", null, personalInfo, null);

        JLabel personalInfoTitle = new JLabel("Personal Info");
        personalInfoTitle.setForeground(new Color(255, 255, 255));
        personalInfoTitle.setFont(new Font("Krungthep", Font.BOLD | Font.ITALIC, 30));
        personalInfoTitle.setBounds(26, 17, 341, 46);
        personalInfo.add(personalInfoTitle);

        JButton btnAccountInfo2 = new JButton("Account Info");
        btnAccountInfo2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tabbedPane.setSelectedIndex(2);
            }
        });
        btnAccountInfo2.setFont(new Font("Krungthep", Font.PLAIN, 14));
        btnAccountInfo2.setBounds(36, 70, 128, 29);
        personalInfo.add(btnAccountInfo2);

        JButton btnHome_1 = new JButton("Home");
        btnHome_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tabbedPane.setSelectedIndex(1);
            }
        });
        btnHome_1.setFont(new Font("Krungthep", Font.PLAIN, 14));
        btnHome_1.setBounds(667, 6, 117, 29);
        personalInfo.add(btnHome_1);
        
        personalTable = new JTable();
        personalTable.setBounds(46, 131, 712, 126);
        personalInfo.add(personalTable);
        
        JButton btnNewButton_2 = new JButton("Update");
        btnNewButton_2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		DefaultTableModel model = (DefaultTableModel) personalTable.getModel();
                int rowCount = model.getRowCount();


                for (int i = 0; i < rowCount; i++) {
                    String id = (String) model.getValueAt(i, 0); // Assuming the first column contains ID


                }

                JOptionPane.showMessageDialog(null, "Personal information updated successfully!");
        	}
        });
        btnNewButton_2.setBounds(46, 283, 117, 29);
        personalInfo.add(btnNewButton_2);
        
        JLabel lblDateOfBirth = new JLabel("Date of Birth");
        lblDateOfBirth.setForeground(new Color(255, 255, 255));
        lblDateOfBirth.setBounds(46, 111, 80, 16);
        personalInfo.add(lblDateOfBirth);
        
        JLabel lblGender = new JLabel("Gender");
        lblGender.setForeground(new Color(255, 255, 255));
        lblGender.setBounds(174, 111, 80, 16);
        personalInfo.add(lblGender);
        
        JLabel lblAddress = new JLabel("Address");
        lblAddress.setForeground(new Color(255, 255, 255));
        lblAddress.setBounds(309, 111, 80, 16);
        personalInfo.add(lblAddress);
        
        JLabel lblCity = new JLabel("City");
        lblCity.setForeground(new Color(255, 255, 255));
        lblCity.setBounds(443, 111, 80, 16);
        personalInfo.add(lblCity);
        
        JLabel lblState = new JLabel("State");
        lblState.setForeground(new Color(255, 255, 255));
        lblState.setBounds(550, 111, 80, 16);
        personalInfo.add(lblState);
        
        JLabel lblZipCode = new JLabel("Zip Code");
        lblZipCode.setForeground(new Color(255, 255, 255));
        lblZipCode.setBounds(662, 111, 80, 16);
        personalInfo.add(lblZipCode);

        JPanel studentInfo = new JPanel();
        studentInfo.setLayout(null);
        studentInfo.setBackground(new Color(128, 0, 0));
        tabbedPane.addTab("New tab", null, studentInfo, null);

        JLabel studentInfoTitle = new JLabel("Student Info");
        studentInfoTitle.setForeground(new Color(255, 255, 255));
        studentInfoTitle.setFont(new Font("Krungthep", Font.BOLD | Font.ITALIC, 30));
        studentInfoTitle.setBounds(26, 17, 341, 46);
        studentInfo.add(studentInfoTitle);

        JButton btnHome_2 = new JButton("Home");
        btnHome_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tabbedPane.setSelectedIndex(1);
            }
        });
        btnHome_2.setFont(new Font("Krungthep", Font.PLAIN, 14));
        btnHome_2.setEnabled(true);
        btnHome_2.setBounds(667, 6, 117, 29);
        studentInfo.add(btnHome_2);
        
        studentTable = new JTable();
        studentTable.setBounds(50, 112, 545, 149);
        studentInfo.add(studentTable);
        
        JLabel lblNewLabel_2 = new JLabel("Student ID");
        lblNewLabel_2.setForeground(new Color(255, 255, 255));
        lblNewLabel_2.setBounds(98, 87, 80, 14);
        studentInfo.add(lblNewLabel_2);
        
        JLabel lblNewLabel_2_1 = new JLabel("Grade Point Average");
        lblNewLabel_2_1.setForeground(new Color(255, 255, 255));
        lblNewLabel_2_1.setBounds(262, 87, 124, 14);
        studentInfo.add(lblNewLabel_2_1);
        
        JLabel lblNewLabel_2_1_1 = new JLabel("Major");
        lblNewLabel_2_1_1.setForeground(new Color(255, 255, 255));
        lblNewLabel_2_1_1.setBounds(460, 87, 105, 14);
        studentInfo.add(lblNewLabel_2_1_1);

        JPanel courseInfo = new JPanel();
        courseInfo.setLayout(null);
        courseInfo.setBackground(new Color(128, 0, 0));
        tabbedPane.addTab("New tab", null, courseInfo, null);

        JLabel courseInfoTitle = new JLabel("Course Info");
        courseInfoTitle.setForeground(new Color(255, 255, 255));
        courseInfoTitle.setFont(new Font("Krungthep", Font.BOLD | Font.ITALIC, 30));
        courseInfoTitle.setBounds(26, 17, 341, 46);
        courseInfo.add(courseInfoTitle);

        JButton btnHome_2_1 = new JButton("Home");
        btnHome_2_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tabbedPane.setSelectedIndex(1);
            }
        });
        btnHome_2_1.setFont(new Font("Krungthep", Font.PLAIN, 14));
        btnHome_2_1.setEnabled(true);
        btnHome_2_1.setBounds(667, 6, 117, 29);
        courseInfo.add(btnHome_2_1);
        
        courseTable = new JTable();
        courseTable.setBounds(26, 96, 593, 108);
        courseInfo.add(courseTable);
        
        table_3 = new JTable();
        table_3.setBounds(619, 96, 103, 108);
        courseInfo.add(table_3);
        
        JLabel lblCourseId = new JLabel("Course ID");
        lblCourseId.setForeground(new Color(255, 255, 255));
        lblCourseId.setBounds(36, 75, 80, 16);
        courseInfo.add(lblCourseId);
        
        JLabel lblCourseName = new JLabel("Course Name");
        lblCourseName.setForeground(new Color(255, 255, 255));
        lblCourseName.setBounds(171, 75, 90, 16);
        courseInfo.add(lblCourseName);
        
        JLabel lblCourseDescription = new JLabel("Course Description");
        lblCourseDescription.setForeground(new Color(255, 255, 255));
        lblCourseDescription.setBounds(315, 75, 127, 16);
        courseInfo.add(lblCourseDescription);
        
        JLabel lblCourseCredits = new JLabel("Course Credits");
        lblCourseCredits.setForeground(new Color(255, 255, 255));
        lblCourseCredits.setBounds(482, 75, 103, 16);
        courseInfo.add(lblCourseCredits);
        
        JLabel lblAttendance = new JLabel("Attendance");
        lblAttendance.setForeground(new Color(255, 255, 255));
        lblAttendance.setBounds(619, 75, 80, 16);
        courseInfo.add(lblAttendance);
        
        JPanel Adminhome = new JPanel();
        Adminhome.setLayout(null);
        Adminhome.setBackground(new Color(128, 0, 0));
        tabbedPane.addTab("New tab", null, Adminhome, null);
        
        JLabel SJU_1 = new JLabel("St. John's University");
        SJU_1.setForeground(new Color(255, 255, 255));
        SJU_1.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 30));
        SJU_1.setBounds(243, 46, 341, 46);
        Adminhome.add(SJU_1);
        
        JButton btnAccountInfo_1 = new JButton("Student Information");
        btnAccountInfo_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		tabbedPane.setSelectedIndex(8);
        	}
        });
        btnAccountInfo_1.setFont(new Font("Dialog", Font.PLAIN, 18));
        btnAccountInfo_1.setBounds(286, 137, 212, 79);
        Adminhome.add(btnAccountInfo_1);
        
        JButton btnStudentInfo_1 = new JButton("Available Courses");
        btnStudentInfo_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		tabbedPane.setSelectedIndex(9);
        	}
        });
        btnStudentInfo_1.setFont(new Font("Dialog", Font.PLAIN, 18));
        btnStudentInfo_1.setBounds(286, 241, 212, 79);
        Adminhome.add(btnStudentInfo_1);
        
        JButton btnLogOut_1 = new JButton("Log Out");
        btnLogOut_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		tabbedPane.setSelectedIndex(3);
        	}
        });
        btnLogOut_1.setFont(new Font("Dialog", Font.PLAIN, 14));
        btnLogOut_1.setBounds(651, 23, 117, 29);
        Adminhome.add(btnLogOut_1);
        
        JPanel AdminSTInfo = new JPanel();
        AdminSTInfo.setLayout(null);
        AdminSTInfo.setBackground(new Color(128, 0, 0));
        tabbedPane.addTab("New tab", null, AdminSTInfo, null);
        
        JLabel lblStudentInformation = new JLabel("Student Information");
        lblStudentInformation.setForeground(new Color(255, 255, 255));
        lblStudentInformation.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 30));
        lblStudentInformation.setBounds(26, 17, 341, 46);
        AdminSTInfo.add(lblStudentInformation);
        
        JButton btnHome_3 = new JButton("Home");
        btnHome_3.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		tabbedPane.setSelectedIndex(7);
        	}
        });
        btnHome_3.setFont(new Font("Dialog", Font.PLAIN, 14));
        btnHome_3.setEnabled(true);
        btnHome_3.setBounds(667, 6, 117, 29);
        AdminSTInfo.add(btnHome_3);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(38, 179, 538, 126);
        AdminSTInfo.add(scrollPane);
        
        table_1 = new JTable();
        scrollPane.setViewportView(table_1);
        
        JLabel lblNewLabel_1 = new JLabel("Student ID\r\n");
        lblNewLabel_1.setForeground(new Color(255, 255, 255));
        lblNewLabel_1.setBounds(46, 110, 87, 16);
        AdminSTInfo.add(lblNewLabel_1);
        
        JLabel lblFirstName_1 = new JLabel("First Name\r\n");
        lblFirstName_1.setForeground(new Color(255, 255, 255));
        lblFirstName_1.setBounds(145, 109, 87, 16);
        AdminSTInfo.add(lblFirstName_1);
        
        JLabel lblLastName_1 = new JLabel("Last Name");
        lblLastName_1.setForeground(new Color(255, 255, 255));
        lblLastName_1.setBounds(282, 110, 67, 16);
        AdminSTInfo.add(lblLastName_1);
        
        JLabel lblEmail_2_2 = new JLabel("Email");
        lblEmail_2_2.setForeground(new Color(255, 255, 255));
        lblEmail_2_2.setBounds(381, 110, 86, 16);
        AdminSTInfo.add(lblEmail_2_2);
        
        JLabel lblEmail_2_1_1 = new JLabel("Phone Number\r\n");
        lblEmail_2_1_1.setForeground(new Color(255, 255, 255));
        lblEmail_2_1_1.setBounds(466, 110, 111, 16);
        AdminSTInfo.add(lblEmail_2_1_1);
        
        textField_1 = new JTextField();
        textField_1.setBounds(143, 138, 96, 20);
        AdminSTInfo.add(textField_1);
        textField_1.setColumns(10);
        
        textField_2 = new JTextField();
        textField_2.setColumns(10);
        textField_2.setBounds(263, 138, 96, 20);
        AdminSTInfo.add(textField_2);
        
        textField_3 = new JTextField();
        textField_3.setColumns(10);
        textField_3.setBounds(371, 138, 96, 20);
        AdminSTInfo.add(textField_3);
        
        textField_4 = new JTextField();
        textField_4.setColumns(10);
        textField_4.setBounds(481, 138, 96, 20);
        AdminSTInfo.add(textField_4);
        
        JButton btnUpdate = new JButton("Update");
        btnUpdate.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		 try {
        	            DefaultTableModel model = (DefaultTableModel) table_1.getModel();
        	            model.setRowCount(0); // Clear the existing rows in the table
        	            Statement stmt = SISConnection.con.createStatement();
        	            ResultSet rs = stmt.executeQuery("SELECT * FROM Student");
        	            while (rs.next()) {
        	                String id = rs.getString("idStudent");
        	                String firstName = rs.getString("firstName");
        	                String lastName = rs.getString("lastName");
        	                String email = rs.getString("email");
        	                String phoneNumber = rs.getString("phoneNumber");
        	                model.addRow(new Object[]{id, firstName, lastName, email, phoneNumber});
        	            }
        	            rs.close();
        	            stmt.close();
        	        } catch (Exception ex) {
        	            ex.printStackTrace();
        	        }
        	}
        });
        btnUpdate.setBounds(625, 107, 89, 23);
        AdminSTInfo.add(btnUpdate);
        
        JButton btnRefresh = new JButton("refresh");
        btnRefresh.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		clearTable(table_1);
                try {
                    String username = textField.getText();

                    String sql = "select idStudent, firstName, lastName, email, phoneNumber from Student";
                    ResultSet rs4=SISConnection.st.executeQuery(sql);
                    ResultSetMetaData rsmd=rs4.getMetaData();
                    DefaultTableModel model=(DefaultTableModel) table_1.getModel();

                    int cols=rsmd.getColumnCount();
                    String[] colName=new String[cols];
                    for(int i=0;i<cols;i++)
                        colName[i]=rsmd.getColumnName(i+1);
                    model.setColumnIdentifiers(colName);
                    String fname, lname, email, phoneNumber,StudentID;
                    while(rs4.next()) {
                    	StudentID=rs4.getString("idStudent");
                        fname=rs4.getString("firstName");
                        lname=rs4.getString("lastName");
                        email=rs4.getString("email");
                        phoneNumber=rs4.getString("phoneNumber");
                        String[] row= {StudentID,fname,lname,email,phoneNumber};
                        model.addRow(row);
                    }
                } catch (Exception e3) {
                    e3.printStackTrace();
                }
        	}
        });
        btnRefresh.setBounds(625, 290, 89, 23);
        AdminSTInfo.add(btnRefresh);
        
        JButton btnDelete = new JButton("Delete");
        btnDelete.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		int selectedRow = table_1.getSelectedRow(); // Get the index of the selected row
                if (selectedRow != -1) { // If a row is selected
                    DefaultTableModel model = (DefaultTableModel) table_1.getModel();
                    String studentID = model.getValueAt(selectedRow, 0).toString(); // Get the student ID from the selected row
                    try {
                        // Prepare SQL statement to delete the row from the database
                        String sql = "DELETE FROM Student WHERE idStudent=?";
                        PreparedStatement stmt = SISConnection.con.prepareStatement(sql);
                        stmt.setString(1, studentID); // Set the student ID as a parameter
                        int rowsAffected = stmt.executeUpdate(); // Execute the SQL statement
                        if (rowsAffected > 0) {
                            // If deletion is successful, remove the row from the JTable
                            model.removeRow(selectedRow);
                            JOptionPane.showMessageDialog(null, "Row deleted successfully!");
                        } else {
                            JOptionPane.showMessageDialog(null, "Error: No rows affected. Row may not exist or database connection issue.");
                        }
                        stmt.close(); // Close the statement
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, "SQL Error: " + ex.getMessage());
                        ex.printStackTrace(); // Print the stack trace for detailed error information
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a row to delete!");
                }
        	}
        });
        btnDelete.setBounds(625, 220, 89, 23);
        AdminSTInfo.add(btnDelete);
      
        textField_5 = new JTextField();
        textField_5.setColumns(10);
        textField_5.setBounds(37, 137, 96, 20);
        AdminSTInfo.add(textField_5);
        
        JButton btnAdd = new JButton("Add");
        btnAdd.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		try {
                    // Get the values from the text fields
                    String studentID = textField_5.getText();
                    String firstName = textField_1.getText();
                    String lastName = textField_2.getText();
                    String email = textField_3.getText();
                    String phoneNumber = textField_4.getText();

                    // Prepare the SQL statement for insertion
                    String sql = "INSERT INTO Student (idStudent, firstName, lastName, email, phoneNumber) VALUES (?, ?, ?, ?, ?)";
                    PreparedStatement statement = SISConnection.con.prepareStatement(sql);

                    // Set the values for the parameters in the SQL statement
                    statement.setString(1, studentID);
                    statement.setString(2, firstName);
                    statement.setString(3, lastName);
                    statement.setString(4, email);
                    statement.setString(5, phoneNumber);

                    // Execute the insertion query
                    int rowsInserted = statement.executeUpdate();
                    if (rowsInserted > 0) {
                        JOptionPane.showMessageDialog(btnAdd, "Student added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

                        // Optionally, clear the text fields after successful insertion
                        textField_5.setText("");
                        textField_1.setText("");
                        textField_2.setText("");
                        textField_3.setText("");
                        textField_4.setText("");
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(btnAdd, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
        	}
        });
        btnAdd.setBounds(625, 165, 89, 23);
        AdminSTInfo.add(btnAdd);
        
        JPanel AdminAvailableCourses = new JPanel();
        AdminAvailableCourses.setLayout(null);
        AdminAvailableCourses.setBackground(new Color(128, 0, 0));
        tabbedPane.addTab("New tab", null, AdminAvailableCourses, null);
        
        JLabel lblAvailableCourses = new JLabel("Available Courses");
        lblAvailableCourses.setForeground(new Color(255, 255, 255));
        lblAvailableCourses.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 30));
        lblAvailableCourses.setBounds(26, 17, 341, 46);
        AdminAvailableCourses.add(lblAvailableCourses);
        
        JButton btnHome_3_1 = new JButton("Home");
        btnHome_3_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		tabbedPane.setSelectedIndex(7);
        	}
        });
        btnHome_3_1.setFont(new Font("Dialog", Font.PLAIN, 14));
        btnHome_3_1.setEnabled(true);
        btnHome_3_1.setBounds(667, 6, 117, 29);
        AdminAvailableCourses.add(btnHome_3_1);
        
        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setBounds(26, 169, 599, 126);
        AdminAvailableCourses.add(scrollPane_1);
        
        table_2 = new JTable();
        scrollPane_1.setViewportView(table_2);
        
        JLabel lblNewLabel_1_1 = new JLabel("Course ID");
        lblNewLabel_1_1.setForeground(new Color(255, 255, 255));
        lblNewLabel_1_1.setBounds(66, 111, 86, 16);
        AdminAvailableCourses.add(lblNewLabel_1_1);
        
        JLabel lblFirstName_1_1 = new JLabel("Course Name");
        lblFirstName_1_1.setForeground(new Color(255, 255, 255));
        lblFirstName_1_1.setBounds(192, 111, 84, 16);
        AdminAvailableCourses.add(lblFirstName_1_1);
        
        JLabel lblLastName_1_1 = new JLabel("Course Description");
        lblLastName_1_1.setForeground(new Color(255, 255, 255));
        lblLastName_1_1.setBounds(328, 111, 137, 16);
        AdminAvailableCourses.add(lblLastName_1_1);
        
        JLabel lblEmail_2_2_1 = new JLabel("Course Credits");
        lblEmail_2_2_1.setForeground(new Color(255, 255, 255));
        lblEmail_2_2_1.setBounds(477, 111, 106, 16);
        AdminAvailableCourses.add(lblEmail_2_2_1);
        
        textField_6 = new JTextField();
        textField_6.setColumns(10);
        textField_6.setBounds(192, 139, 96, 20);
        AdminAvailableCourses.add(textField_6);
        
        textField_7 = new JTextField();
        textField_7.setColumns(10);
        textField_7.setBounds(338, 138, 96, 20);
        AdminAvailableCourses.add(textField_7);
        
        textField_8 = new JTextField();
        textField_8.setColumns(10);
        textField_8.setBounds(477, 138, 96, 20);
        AdminAvailableCourses.add(textField_8);
        
        JButton btnNewButton_1 = new JButton("Add");
        btnNewButton_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		try {
                    // Get the values from the text fields
                    String courseID = textField_10.getText();
                    String courseName = textField_6.getText();
                    String courseDesc = textField_7.getText();
                    String courseCredit = textField_8.getText();

                    // Prepare the SQL statement for insertion
                    String sql = "INSERT INTO courses (idcourses, courseName, courseDescription, courseCredit) VALUES (?, ?, ?, ?)";
                    PreparedStatement statement = SISConnection.con.prepareStatement(sql);

                    // Set the values for the parameters in the SQL statement
                    statement.setString(1, courseID);
                    statement.setString(2, courseName);
                    statement.setString(3, courseDesc);
                    statement.setString(4, courseCredit);

                    // Execute the insertion query
                    int rowsInserted = statement.executeUpdate();
                    if (rowsInserted > 0) {
                        JOptionPane.showMessageDialog(btnAdd, "Course added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

                        // Optionally, clear the text fields after successful insertion
                        textField_5.setText("");
                        textField_1.setText("");
                        textField_2.setText("");
                        textField_3.setText("");
                        textField_4.setText("");
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(btnAdd, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
        	}
        });
        btnNewButton_1.setBounds(642, 109, 89, 23);
        AdminAvailableCourses.add(btnNewButton_1);
        
        JButton btnUpdate_1 = new JButton("Update");
        btnUpdate_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		try {
    	            DefaultTableModel model = (DefaultTableModel) table_2.getModel();
    	            model.setRowCount(0); // Clear the existing rows in the table
    	            Statement stmt = SISConnection.con.createStatement();
    	            ResultSet rs = stmt.executeQuery("SELECT * FROM courses");
    	            while (rs.next()) {
    	                String courseid = rs.getString("idcourses");
    	                String courseName = rs.getString("courseName");
    	                String courseDesc = rs.getString("courseDescription");
    	                String courseCredit = rs.getString("courseCredit");
    	                model.addRow(new Object[]{courseid, courseName, courseDesc, courseCredit});
    	            }
    	            rs.close();
    	            stmt.close();
    	        } catch (Exception ex) {
    	            ex.printStackTrace();
    	        }
        	}
        });
        btnUpdate_1.setBounds(642, 160, 89, 23);
        AdminAvailableCourses.add(btnUpdate_1);
        
        JButton btnDelete_1 = new JButton("Delete");
        btnDelete_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		int selectedRow = table_2.getSelectedRow(); // Get the index of the selected row
                if (selectedRow != -1) { // If a row is selected
                    DefaultTableModel model = (DefaultTableModel) table_2.getModel();
                    String courseID = model.getValueAt(selectedRow, 0).toString(); // Get the student ID from the selected row
                    try {
                        // Prepare SQL statement to delete the row from the database
                        String sql = "DELETE FROM courses WHERE idcourses=?";
                        PreparedStatement stmt = SISConnection.con.prepareStatement(sql);
                        stmt.setString(1, courseID); // Set the student ID as a parameter
                        int rowsAffected = stmt.executeUpdate(); // Execute the SQL statement
                        if (rowsAffected > 0) {
                            // If deletion is successful, remove the row from the JTable
                            model.removeRow(selectedRow);
                            JOptionPane.showMessageDialog(null, "Row deleted successfully!");
                        } else {
                            JOptionPane.showMessageDialog(null, "Error: No rows affected. Row may not exist or database connection issue.");
                        }
                        stmt.close(); // Close the statement
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, "SQL Error: " + ex.getMessage());
                        ex.printStackTrace(); // Print the stack trace for detailed error information
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a row to delete!");
                }
        	}
        });
        btnDelete_1.setBounds(642, 209, 89, 23);
        AdminAvailableCourses.add(btnDelete_1);
        
        JButton btnRefresh_1 = new JButton("Refresh");
        btnRefresh_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		clearTable(table_2);
                try {
                    String username = textField.getText();

                    String sql = "select idcourses, courseName, courseDescription, courseCredit from courses";
                    ResultSet rs4=SISConnection.st.executeQuery(sql);
                    ResultSetMetaData rsmd=rs4.getMetaData();
                    DefaultTableModel model=(DefaultTableModel) table_2.getModel();

                    int cols=rsmd.getColumnCount();
                    String[] colName=new String[cols];
                    for(int i=0;i<cols;i++)
                        colName[i]=rsmd.getColumnName(i+1);
                    model.setColumnIdentifiers(colName);
                    String fname, lname, email,StudentID;
                    while(rs4.next()) {
                    	StudentID=rs4.getString("idcourses");
                        fname=rs4.getString("courseName");
                        lname=rs4.getString("courseDescription");
                        email=rs4.getString("courseCredit");
                        String[] row= {StudentID,fname,lname,email};
                        model.addRow(row);
                    }
                } catch (Exception e3) {
                    e3.printStackTrace();
                }
        	}
        });
        btnRefresh_1.setBounds(642, 258, 89, 23);
        AdminAvailableCourses.add(btnRefresh_1);
        
        textField_10 = new JTextField();
        textField_10.setColumns(10);
        textField_10.setBounds(56, 138, 96, 20);
        AdminAvailableCourses.add(textField_10);
        
        JButton PersonalInfoUpdate = new JButton("Update");
        PersonalInfoUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                DefaultTableModel model = (DefaultTableModel) personalTable.getModel();
                int rowCount = model.getRowCount();


                for (int i = 0; i < rowCount; i++) {
                    String id = (String) model.getValueAt(i, 0); // Assuming the first column contains ID


                }

                JOptionPane.showMessageDialog(null, "Personal information updated successfully!");
            }
        });
        
        JButton AccInfoUpdate = new JButton("Update");
        AccInfoUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                DefaultTableModel model = (DefaultTableModel) AccountTable.getModel();
                int rowCount = model.getRowCount();

                // Update database with the new data
                for (int i = 0; i < rowCount; i++) {
                    String id = (String) model.getValueAt(i, 0); // Assuming the first column contains ID

                }

                JOptionPane.showMessageDialog(null, "Account information updated successfully!");
            }
        });
    }
}
