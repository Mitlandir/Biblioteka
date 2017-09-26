/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Klase.Limit;
import Klase.Clanovi;
import Klase.HibernateUtil;
import Klase.Iznajmljeno;
import Klase.Knjige;
import java.awt.event.ComponentAdapter;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.PatternSyntaxException;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import org.hibernate.NonUniqueObjectException;
import org.hibernate.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class ClanoviGUI extends javax.swing.JFrame {

    Session s = HibernateUtil.getSessionFactory().openSession();
    Query q;

    private class Nit extends Thread {

        public void run() {
            LocalDate now = LocalDate.now();

            q = s.createQuery("from Iznajmljeno");
            if (q.list().isEmpty()) {
                return;
            }
            List<Iznajmljeno> lista = new ArrayList<>(q.list());
            List<Iznajmljeno> pozajmice = new ArrayList<>();
            for (int i = 0; i < lista.size(); i++) {
                LocalDate date = LocalDate.parse(lista.get(i).getDatum());
                if (date.isEqual(now)) {
                    pozajmice.add(lista.get(i));
                }
            }
            Object[] poz = pozajmice.toArray();
            if(poz.length==0){
                return;
            }
            JOptionPane.showInputDialog(getContentPane(), "Kriticne pozajmice !", null, JOptionPane.WARNING_MESSAGE, null, poz, false);

        }
    }

    public ClanoviGUI() {
        initComponents();
        new Nit().start();

        jList1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jList2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jList3.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        jTextField1.setDocument(new Limit(9));
        jTextField2.setDocument(new Limit(20));
        jTextField3.setDocument(new Limit(20));

        jTextField1.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                while (jTextField1.getText().contains("  ")) {
                    jTextField1.setText(jTextField1.getText().replaceAll("  ", " "));
                }
                if (jTextField1.getText().isEmpty()) {
                    jList1.setModel(new DefaultListModel());
                    jList2.setModel(new DefaultListModel());
                    return;
                }
                if (jTextField1.getText().length() == 9) {
                    jTextField2.requestFocusInWindow();
                }
                String text = jTextField1.getText().trim();
                q = s.createQuery("from Clanovi where br_licne_karte like '" + text + "%'");

                DefaultListModel dlm = new DefaultListModel();
                for (int i = 0; i < q.list().size(); i++) {
                    dlm.addElement((Clanovi) q.list().get(i));
                }
                jList1.setModel(dlm);

            }
        });

        jList1.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                Clanovi c = (Clanovi) jList1.getSelectedValue();
                if (c == null) {
                    jList2.setModel(new DefaultListModel());
                    return;
                }
                List<Iznajmljeno> lista = new ArrayList<>(c.getIznajmljeno());
                DefaultListModel dlm = new DefaultListModel();
                for (int i = 0; i < lista.size(); i++) {
                    dlm.addElement(lista.get(i));
                }
                jList2.setModel(dlm);
            }
        });
        jTextField3.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                while (jTextField3.getText().contains("  ")) {
                    jTextField3.setText(jTextField3.getText().replaceAll("  ", " "));
                }
                if (jTextField3.getText().isEmpty()) {
                    jList3.setModel(new DefaultListModel());
                    return;
                }
                String naslov = jTextField3.getText();
                q = s.createQuery("from Knjige where naslov like '" + naslov + "%'");

                DefaultListModel dlm = new DefaultListModel();
                for (int i = 0; i < q.list().size(); i++) {
                    dlm.addElement((Knjige) q.list().get(i));
                }
                jList3.setModel(dlm);

            }
        });
        jTextField4.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                while (jTextField4.getText().contains("  ")) {
                    jTextField4.setText(jTextField4.getText().replaceAll("  ", " "));
                }
            }
        });
        jTextField2.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                while (jTextField2.getText().contains("  ")) {
                    jTextField2.setText(jTextField2.getText().replaceAll("  ", " "));
                }
            }
        });

        jList3.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                Knjige k = (Knjige) jList3.getSelectedValue();
                if (k == null) {
                    jList4.setModel(new DefaultListModel());
                    return;
                }
                List<Iznajmljeno> lista = new ArrayList<>(k.getIznajmljeno());
                DefaultListModel dlm = new DefaultListModel();
                for (int i = 0; i < lista.size(); i++) {
                    dlm.addElement(lista.get(i).getClanovi());
                }
                jList4.setModel(dlm);
            }
        });

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jLabel2 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList2 = new javax.swing.JList();
        jButton2 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        jList3 = new javax.swing.JList();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        jList4 = new javax.swing.JList();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(204, 255, 204));
        setBounds(new java.awt.Rectangle(0, 0, 838, 409));
        setMaximumSize(new java.awt.Dimension(838, 409));
        setMinimumSize(new java.awt.Dimension(838, 409));
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel1.setText("Broj licne karte");

        jTextField1.setSelectionEnd(9);

        jScrollPane1.setViewportView(jList1);

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel2.setText("Ime i prezime");

        jButton1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jButton1.setText("Upisi clana");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jList2.setForeground(new java.awt.Color(153, 0, 0));
        jScrollPane2.setViewportView(jList2);

        jButton2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jButton2.setText("Vrati knjigu");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel3.setText("Naslov Knjige");

        jScrollPane3.setViewportView(jList3);

        jButton3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jButton3.setText("Iznajmi");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jButton4.setText("Unesi knjigu");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel4.setText("Kolicina");

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel5.setText("Autor");

        jButton5.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jButton5.setText("Ukloni clana");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jButton6.setText("Ukloni knjigu ");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jScrollPane4.setViewportView(jList4);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(jLabel2))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel5))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel4)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 294, Short.MAX_VALUE)
                    .addComponent(jScrollPane3)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jButton5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
                        .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jButton4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(255, 255, 255))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (jTextField1.getText().length() < 9) {
            JOptionPane.showMessageDialog(getContentPane(), "Broj licne karte mora sadrzati 9 cifara !", null, JOptionPane.ERROR_MESSAGE);
            jTextField1.requestFocusInWindow();
            return;
        }
        for (int i = 0; i < jTextField1.getText().length(); i++) {
            char pom = jTextField1.getText().charAt(i);
            if (Character.isDigit(pom) == false) {
                JOptionPane.showMessageDialog(getContentPane(), "Broj licne karte moze sadrzati samo cifre !", null, JOptionPane.ERROR_MESSAGE);
                jTextField1.requestFocusInWindow();
                return;
            }
        }
        String br = jTextField1.getText();

        while (jTextField2.getText().contains("  ")) {
            jTextField2.setText(jTextField2.getText().replaceAll("  ", " "));
        }
        for (int i = 0; i < jTextField2.getText().length(); i++) {
            char pom = jTextField2.getText().charAt(i);
            if (Character.isAlphabetic(pom) == false && Character.isWhitespace(pom) == false) {

                JOptionPane.showMessageDialog(getContentPane(), "Ime i prezime mora sadrzati samo slova !", null, JOptionPane.ERROR_MESSAGE);
                jTextField2.requestFocusInWindow();

                return;

            }

        }
        if (jTextField2.getText().length() < 5) {
            JOptionPane.showMessageDialog(getContentPane(), "Ime i prezime ne moze da bude krace od 5 polja !", null, JOptionPane.ERROR_MESSAGE);
            jTextField2.requestFocusInWindow();
            return;
        }
        String ime = jTextField2.getText();
        Clanovi c = new Clanovi(br, ime);
        Transaction t = s.beginTransaction();
        try {
            s.save(c);
        } catch (NonUniqueObjectException e) {
            JOptionPane.showMessageDialog(getContentPane(), "Vec postoji clan sa tim brojem licne karte !", null, JOptionPane.ERROR_MESSAGE);
            t.commit();
            return;
        }
        t.commit();

        jTextField1.setText("");
        jTextField2.setText("");
        jTextField1.requestFocusInWindow();
        JOptionPane.showMessageDialog(getContentPane(), "Clan uspesno upisan !", null, JOptionPane.INFORMATION_MESSAGE
        );

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        Iznajmljeno iz = (Iznajmljeno) jList2.getSelectedValue();
        Knjige k1 = (Knjige) jList3.getSelectedValue();
        if (iz == null) {
            JOptionPane.showMessageDialog(getContentPane(), "Morate selektovati pozajmicu koja je razduzena !", null, JOptionPane.ERROR_MESSAGE);
            return;
        }
        Transaction t = s.beginTransaction();
        Knjige k = iz.getKnjige();
        Clanovi c = iz.getClanovi();
        c.getIznajmljeno().remove(iz);
        k.getIznajmljeno().remove(iz);
        k.setKolicina(k.getKolicina() + 1);
        s.delete(iz);
        s.save(k);
        s.save(c);

        t.commit();
        DefaultListModel dlm = (DefaultListModel) jList2.getModel();
        dlm.removeElement(iz);
        jList2.setModel(dlm);
        if (k1.equals(k)) {

            dlm = (DefaultListModel) jList4.getModel();

            dlm.removeElement(iz.getClanovi());
            jList4.setModel(dlm);

        }

        JOptionPane.showMessageDialog(getContentPane(), "Uspesno vraceno !", null, JOptionPane.INFORMATION_MESSAGE);

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        Clanovi c = (Clanovi) jList1.getSelectedValue();
        Knjige k = (Knjige) jList3.getSelectedValue();
        if (c == null) {
            JOptionPane.showMessageDialog(getContentPane(), "Clan nije izabran !", null, JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (k == null) {
            JOptionPane.showMessageDialog(getContentPane(), "Knjiga nije izabrana !", null, JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (k.getKolicina() == 0) {
            JOptionPane.showMessageDialog(getContentPane(), "Nema vise primeraka ove knjige !", null, JOptionPane.ERROR_MESSAGE);
            return;
        }
        Iznajmljeno iz = new Iznajmljeno(c, k);
        Transaction t = s.beginTransaction();
        try {
            k.setKolicina(k.getKolicina() - 1);
            k.getIznajmljeno().add(iz);
            c.getIznajmljeno().add(iz);
            s.save(iz);
            s.save(k);
            s.save(c);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(getContentPane(), "Ovaj clan vec poseduje primerak ove knjige !", null, JOptionPane.ERROR_MESSAGE);
            t.commit();
            return;

        }
        t.commit();
        JOptionPane.showMessageDialog(getContentPane(), "Uspesno izajmljeno !", null, JOptionPane.INFORMATION_MESSAGE);
        jList1.requestFocusInWindow();
        jList2.requestFocusInWindow();
        jList3.requestFocusInWindow();
        List<Iznajmljeno> lista = new ArrayList<>(c.getIznajmljeno());
        DefaultListModel dlm = new DefaultListModel();
        for (int i = 0; i < lista.size(); i++) {
            dlm.addElement(lista.get(i));
        }
        jList2.setModel(dlm);
        List<Iznajmljeno> lista1 = new ArrayList<>(k.getIznajmljeno());
        DefaultListModel dlm1 = new DefaultListModel();
        for (int i = 0; i < lista1.size(); i++) {
            dlm1.addElement(lista1.get(i).getClanovi());
        }
        jList4.setModel(dlm1);

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

        while (jTextField3.getText().contains("  ")) {
            jTextField3.setText(jTextField3.getText().replaceAll("  ", " "));
        }
        if (jTextField3.getText().length() < 5) {
            JOptionPane.showMessageDialog(getContentPane(), "Naslov knjige ne moze da ima manje od 5 karaktera !", null, JOptionPane.ERROR_MESSAGE);
            jTextField3.requestFocusInWindow();
            return;
        }
        while (jTextField4.getText().contains("  ")) {
            jTextField4.setText(jTextField4.getText().replaceAll("  ", " "));
        }
        if (jTextField4.getText().length() < 5) {
            JOptionPane.showMessageDialog(getContentPane(), "Ime autora ne moze da ima manje od 5 karaktera !", null, JOptionPane.ERROR_MESSAGE);
            jTextField4.requestFocusInWindow();
            return;
        }
        for (int i = 0; i < jTextField4.getText().length(); i++) {
            char pom = jTextField4.getText().charAt(i);
            if (Character.isAlphabetic(pom) == false && Character.isWhitespace(pom) == false) {
                JOptionPane.showMessageDialog(getContentPane(), "Ime autora moze sadrzati samo karaktere !", null, JOptionPane.ERROR_MESSAGE);
                jTextField4.requestFocusInWindow();
                return;
            }
        }
        String kolicina = jTextField5.getText().trim();
        int kol = 0;
        try {
            kol = Integer.valueOf(kolicina);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(getContentPane(), "Kolicina mora biti predstavljena celobrojnom vrednoscu !", null, JOptionPane.ERROR_MESSAGE);
            jTextField4.requestFocusInWindow();
            return;
        }
        String naslov = jTextField3.getText().trim();
        String autor = jTextField4.getText().trim();
        Knjige k = new Knjige(naslov, autor, kol);
        Transaction t = s.beginTransaction();
        s.save(k);
        t.commit();
        jTextField3.setText("");
        jTextField4.setText("");
        jTextField5.setText("");
        JOptionPane.showMessageDialog(getContentPane(), "Knjiga uspesno uneta !", null, JOptionPane.INFORMATION_MESSAGE);
        jTextField3.requestFocusInWindow();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        Clanovi c = (Clanovi) jList1.getSelectedValue();
        if (c == null) {
            JOptionPane.showMessageDialog(getContentPane(), "Morate selektovati clana !", null, JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (c.getIznajmljeno().isEmpty() == false) {
            JOptionPane.showMessageDialog(getContentPane(), "Clan ne moze biti uklonjen jer ima zaduzenja !", null, JOptionPane.ERROR_MESSAGE);
            return;
        }
        Transaction t = s.beginTransaction();
        s.delete(c);
        t.commit();
        DefaultListModel dlm = (DefaultListModel) jList1.getModel();
        dlm.removeElement(c);
        jList1.setModel(dlm);
        JOptionPane.showMessageDialog(getContentPane(), "Clan uspesno uklonjen !", null, JOptionPane.INFORMATION_MESSAGE);

    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        Knjige k = (Knjige) jList3.getSelectedValue();
        if (k == null) {
            JOptionPane.showMessageDialog(getContentPane(), "Morate selektovati knjigu !", null, JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (k.getIznajmljeno().isEmpty() == false) {
            JOptionPane.showMessageDialog(getContentPane(), "Ne mozete ukloniti knjigu jer ima iznajmljenih primeraka !", null, JOptionPane.ERROR_MESSAGE);
            return;
        }
        Transaction t = s.beginTransaction();
        s.delete(k);
        t.commit();
        DefaultListModel dlm = (DefaultListModel) jList3.getModel();
        dlm.removeElement(k);
        jList3.setModel(dlm);
        JOptionPane.showMessageDialog(getContentPane(), "Knjiga uspesno uklonjena !", null, JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_jButton6ActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ClanoviGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JList jList1;
    private javax.swing.JList jList2;
    private javax.swing.JList jList3;
    private javax.swing.JList jList4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    // End of variables declaration//GEN-END:variables
}
