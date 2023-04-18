package Interfaz;

import Interfaz.InterfazArbol;
import clases.Persona;
import clases.Arbol;
import clases.ListaPersonas;
import clases.Nodo;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Rodriguez
 */
public class InterfazMenu extends javax.swing.JFrame {

    static boolean Ingresar;
    static boolean padres = false;
    static boolean abuelos = false;
    static Persona usuario = new Persona("");
    static Arbol ArbolGenealogico = new Arbol(usuario);
    static Persona abueloPaterno = new Persona("");
    static Persona abuelaPaterna = new Persona("");
    static Persona abueloMaterno = new Persona("");
    static Persona abuelaMaterna = new Persona("");
    static Persona madre = new Persona("");
    static Persona padre = new Persona("");
    static Color colorFondo = new Color(255, 255, 255);
    static String barra = File.separator;

    public void configuracion() {
        abueloPaterno.setPareja(abuelaPaterna);
        abueloMaterno.setPareja(abuelaMaterna);
        abuelaMaterna.setPareja(abueloMaterno);
        abuelaPaterna.setPareja(abueloPaterno);
        madre.setPadre(abueloMaterno);
        madre.setMadre(abuelaMaterna);
        padre.setPadre(abueloPaterno);
        padre.setMadre(abuelaPaterna);
        padre.setPareja(madre);
        usuario.setPadre(padre);
        usuario.setMadre(madre);
    }

    public void mensaje(String cadena) {
        setEnabled(false);
        JDialog dialogo = new JDialog();
        dialogo.setLayout(new GridBagLayout());
        Font grandeFuente = new Font("Montserrat", 0, 24);
        JLabel etiqueta = new JLabel(cadena);
        etiqueta.setFont(grandeFuente);
        etiqueta.setForeground(Color.BLACK);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);
        dialogo.add(etiqueta, gbc);
        gbc.gridy++;
        JButton boton = new JButton("Aceptar");
        Color Azul = Color.decode("#321774");
        boton.setBackground(Azul);
        boton.setForeground(colorFondo);
        boton.setFont(grandeFuente);
        boton.setFocusable(false);
        boton.addActionListener((ActionEvent e) -> {
            setEnabled(true);
            dialogo.dispose();
        });

        dialogo.add(boton, gbc);

        dialogo.pack();

        dialogo.getContentPane().setBackground(colorFondo);
        dialogo.setSize(800, 400);
        dialogo.setLocationRelativeTo(null);
        dialogo.setResizable(false);
        dialogo.setVisible(true);

        dialogo.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                // Acciones a realizar cuando se cierra el diálogo
                setEnabled(true);
            }
        });
    }

    public boolean validarCampos(String valor, String cadena) {
        if (valor.equals(cadena) || valor.equals("")) {
            mensaje("Debe llenar el campo");
            return false;
        }
        if (ArbolGenealogico.buscarPersona(valor) != null) {
            mensaje("Este nombre ya ha sido ingresado colocale un distinttivo");
            return false;
        }

        return true;
    }

    public void eventos(JTextField Campo, String cadena) {
        Campo.addFocusListener(new FocusAdapter() {

            public void focusGained(FocusEvent e) {
                // Acciones a realizar cuando el campo de texto obtiene el foco
                Campo.setText("");
            }
        });

        Campo.addMouseListener(new MouseAdapter() {
            public void mouseExited(MouseEvent e) {
                if (Campo.getText().equals("")) {
                    Campo.setText(cadena);
                }
            }

            public void mouseClicked(MouseEvent e) {
                Campo.setText("");
            }
        });
    }

    public void NuevosIntegrantes(String cadena, int cantidad) {
        //Inhabilitar la ventana
        setEnabled(false);
        JDialog dialogo = new JDialog();
        Color blanco = new Color(255, 255, 255);
        Font grandeFuente = new Font("Arial", Font.BOLD, 18);
        //Asignando un diseño de cuadricula de celdas
        dialogo.setLayout(new GridBagLayout());
        //Crea el mensaje en una etiqueta     
        JLabel etiqueta = new JLabel("Debe ingresar su " + cadena);
        etiqueta.setFont(grandeFuente);
        etiqueta.setForeground(blanco);

        Color Naranja = Color.decode("#FF5733");

        //Se crean campos de texto y se llama a la funcion eventos
        JTextField Campo = new JTextField(cadena);
        eventos(Campo, cadena);

        JTextField CampoPadre = new JTextField("Padre");
        eventos(CampoPadre, "Padre");

        JTextField CampoMadre = new JTextField("Madre");
        eventos(CampoMadre, "Madre");

        JTextField CampoAbueloPaterno = new JTextField("Abuelo Paterno");
        eventos(CampoAbueloPaterno, "Abuelo Paterno");

        JTextField CampoAbuelaPaterno = new JTextField("Abuela Paterno");
        eventos(CampoAbuelaPaterno, "Abuela Paterno");

        JTextField CampoAbueloMaterno = new JTextField("Abuelo Materno");
        eventos(CampoAbueloMaterno, "Abuelo Materno");

        JTextField CampoAbuelaMaterno = new JTextField("Abuela Materna");
        eventos(CampoAbuelaMaterno, "Abuela Materna");

        //GridBagLayout para agregar un componente en una posición específica dentro de la cuadrícula de celdas
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        //El componente se estirará horizontalmente para llenar todo el ancho de su celda en la cuadrícula
        gbc.fill = GridBagConstraints.HORIZONTAL;
        //Son los margenes del dialogo
        gbc.insets = new Insets(10, 10, 10, 10);
        dialogo.add(etiqueta, gbc);
        gbc.gridy++;

        if (cantidad == 1) {
            dialogo.add(Campo, gbc);
            gbc.gridy++;
            JButton boton = new JButton("Aceptar");
            boton.setBackground(Naranja);
            boton.setForeground(blanco);
            boton.setFont(grandeFuente);
            boton.addActionListener((ActionEvent e) -> {
                if (validarCampos(Campo.getText(), cadena)) {
                    if (cadena.equals("Hermano")) {
                        Persona hermano = new Persona(Campo.getText());
                        hermano.setPadre(ArbolGenealogico.getRaiz().getPadre());
                    } else if (cadena.equals("Hijo")) {
                        Persona hijo = new Persona(Campo.getText());
                        hijo.setPadre(ArbolGenealogico.getRaiz());

                    } //el otro caso es la esposa
                    else {
                        Persona Esposa = new Persona(Campo.getText());
                        usuario.setPareja(Esposa);
                    }
                    setEnabled(true);
                    dialogo.dispose();

                    Ingresar = true;
                }
            });
            dialogo.add(boton, gbc);
        } else if (cantidad == 2) {

            CampoPadre.setPreferredSize(new Dimension(200, 25));
            dialogo.add(CampoPadre, gbc);
            gbc.gridy++;
            CampoMadre.setPreferredSize(new Dimension(250, 25));
            dialogo.add(CampoMadre, gbc);
            gbc.gridy++;

            JButton boton = new JButton("Aceptar");
            boton.setBackground(Naranja);
            boton.setForeground(blanco);
            boton.setFont(grandeFuente);
            boton.addActionListener((ActionEvent e) -> {
                if (validarCampos(CampoMadre.getText(), "Madre") && validarCampos(CampoPadre.getText(), "Padre")) {
                    madre.setNombre(CampoMadre.getText());
                    padre.setNombre(CampoPadre.getText());
                    setEnabled(true);
                    dialogo.dispose();

                    Ingresar = true;
                    padres = true;
                }
            });

            dialogo.add(boton, gbc);

        } else if (cantidad == 4) {

            CampoAbueloPaterno.setPreferredSize(new Dimension(200, 25));
            dialogo.add(CampoAbueloPaterno, gbc);
            gbc.gridy++;

            CampoAbuelaPaterno.setPreferredSize(new Dimension(250, 25));
            dialogo.add(CampoAbuelaPaterno, gbc);
            gbc.gridy++;

            CampoAbueloMaterno.setPreferredSize(new Dimension(200, 25));
            dialogo.add(CampoAbueloMaterno, gbc);
            gbc.gridy++;

            CampoAbuelaMaterno.setPreferredSize(new Dimension(250, 25));
            dialogo.add(CampoAbuelaMaterno, gbc);
            gbc.gridy++;

            JButton boton = new JButton("Aceptar");
            boton.setBackground(Naranja);
            boton.setForeground(blanco);
            boton.setFont(grandeFuente);
            boton.addActionListener((ActionEvent e) -> {
                if (validarCampos(CampoAbueloPaterno.getText(), "Abuelo Paterno")
                        && validarCampos(CampoAbuelaPaterno.getText(), "Abuela Paterno")
                        && validarCampos(CampoAbueloMaterno.getText(), "Abuelo Materno")
                        && validarCampos(CampoAbuelaMaterno.getText(), "Abuela Materna")) {
                    abueloPaterno.setNombre(CampoAbueloPaterno.getText());
                    abuelaPaterna.setNombre(CampoAbuelaPaterno.getText());
                    abueloMaterno.setNombre(CampoAbueloMaterno.getText());
                    abuelaMaterna.setNombre(CampoAbuelaMaterno.getText());
                    setEnabled(true);
                    dialogo.dispose();
                    Ingresar = true;
                    abuelos = true;
                }
            });

            dialogo.add(boton, gbc);
        }

        dialogo.pack();
        Color Verde = Color.decode("#2E694B");
        dialogo.getContentPane().setBackground(Verde);
        dialogo.setSize(600, 400);
        dialogo.setLocationRelativeTo(null);
        dialogo.setResizable(false);
        dialogo.setVisible(true);
        Ingresar = false;

        dialogo.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                // Acciones a realizar cuando se cierra el diálogo
                setEnabled(true);
            }
        });

    }
    
    public void saltar(){
        if(abuelos){
            if(padres){
                setVisible(false);
                new InterfazArbol().setVisible(true);
            }
            else{
                mensaje("Debe ingresar sus padres");
            }
        } else{
            mensaje("Debe ingresar sus abuelos");
        }
        
    }

    /**
     * Creates new form Arbol
     */
    public InterfazMenu() {
        initComponents();
        setTitle("Arbol Genealógico");
        jPanel3.setVisible(false);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel4 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        CampoPersona = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        botonIngresar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        BotonIncorporar = new javax.swing.JButton();
        BotonHermano = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        BotonEsposa = new javax.swing.JButton();
        BotonVolverMenu = new javax.swing.JButton();
        BotonAbuelo = new javax.swing.JButton();
        BotonGenerar = new javax.swing.JButton();
        BotonHijos = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(890, 600));
        setPreferredSize(new java.awt.Dimension(890, 600));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setPreferredSize(new java.awt.Dimension(1800, 800));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 153, 0));

        jLabel8.setBackground(new java.awt.Color(255, 255, 255));
        jLabel8.setFont(new java.awt.Font("Montserrat", 1, 48)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Arbol Genealógico");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 888, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 912, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel8)
                .addContainerGap(41, Short.MAX_VALUE))
        );

        jPanel4.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1800, 120));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jPanel2MouseMoved(evt);
            }
        });
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        CampoPersona.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        CampoPersona.setText("Ingrese su nombre");
        CampoPersona.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CampoPersonaMouseClicked(evt);
            }
        });
        jPanel2.add(CampoPersona, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 190, 272, 64));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/imageedit_2_7557569233.png"))); // NOI18N
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 160, 195, 168));
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(135, 85, -1, -1));

        botonIngresar.setBackground(new java.awt.Color(116, 23, 69));
        botonIngresar.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        botonIngresar.setForeground(new java.awt.Color(255, 255, 255));
        botonIngresar.setText("Ingresar");
        botonIngresar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                botonIngresarMouseClicked(evt);
            }
        });
        jPanel2.add(botonIngresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 370, 201, -1));

        jPanel4.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 85, 990, 530));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        BotonIncorporar.setBackground(new java.awt.Color(116, 23, 69));
        BotonIncorporar.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        BotonIncorporar.setForeground(new java.awt.Color(255, 255, 255));
        BotonIncorporar.setText("Padres");
        BotonIncorporar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        BotonIncorporar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonIncorporarActionPerformed(evt);
            }
        });
        jPanel3.add(BotonIncorporar, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 110, 156, 45));

        BotonHermano.setBackground(new java.awt.Color(116, 23, 69));
        BotonHermano.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        BotonHermano.setForeground(new java.awt.Color(255, 255, 255));
        BotonHermano.setText("Herman@");
        BotonHermano.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonHermanoActionPerformed(evt);
            }
        });
        jPanel3.add(BotonHermano, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 230, 156, 45));

        jLabel3.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Añadir");
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 160, -1, 40));

        BotonEsposa.setBackground(new java.awt.Color(116, 23, 69));
        BotonEsposa.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        BotonEsposa.setForeground(new java.awt.Color(255, 255, 255));
        BotonEsposa.setText("Espos@");
        BotonEsposa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonEsposaActionPerformed(evt);
            }
        });
        jPanel3.add(BotonEsposa, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 170, 156, 45));

        BotonVolverMenu.setBackground(new java.awt.Color(23, 115, 116));
        BotonVolverMenu.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        BotonVolverMenu.setText("Volver");
        BotonVolverMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonVolverMenuActionPerformed(evt);
            }
        });
        jPanel3.add(BotonVolverMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, 156, 50));

        BotonAbuelo.setBackground(new java.awt.Color(116, 23, 69));
        BotonAbuelo.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        BotonAbuelo.setForeground(new java.awt.Color(255, 255, 255));
        BotonAbuelo.setText("Abuelos");
        BotonAbuelo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonAbueloActionPerformed(evt);
            }
        });
        jPanel3.add(BotonAbuelo, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 50, 156, 45));

        BotonGenerar.setBackground(new java.awt.Color(23, 68, 116));
        BotonGenerar.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        BotonGenerar.setForeground(new java.awt.Color(255, 255, 255));
        BotonGenerar.setText("Generar");
        BotonGenerar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BotonGenerarMouseClicked(evt);
            }
        });
        BotonGenerar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonGenerarActionPerformed(evt);
            }
        });
        jPanel3.add(BotonGenerar, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 400, 156, 50));

        BotonHijos.setBackground(new java.awt.Color(116, 23, 69));
        BotonHijos.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        BotonHijos.setForeground(new java.awt.Color(255, 255, 255));
        BotonHijos.setText("Hij@");
        BotonHijos.setToolTipText("");
        BotonHijos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonHijosActionPerformed(evt);
            }
        });
        jPanel3.add(BotonHijos, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 290, 156, 45));

        jPanel4.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 890, 510));

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1800, 1070));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void BotonIncorporarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonIncorporarActionPerformed
        // TODO add your handling code here:
        NuevosIntegrantes("Padres", 2);
    }//GEN-LAST:event_BotonIncorporarActionPerformed

    private void BotonHermanoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonHermanoActionPerformed
        // TODO add your handling code here:
        NuevosIntegrantes("Hermano", 1);
    }//GEN-LAST:event_BotonHermanoActionPerformed

    private void BotonHijosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonHijosActionPerformed
        // TODO add your handling code here:
        NuevosIntegrantes("Hijo", 1);
    }//GEN-LAST:event_BotonHijosActionPerformed

    private void BotonVolverMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonVolverMenuActionPerformed
        // TODO add your handling code here:
        jPanel3.setVisible(false);
        jPanel2.setVisible(true);
    }//GEN-LAST:event_BotonVolverMenuActionPerformed

    private void BotonEsposaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonEsposaActionPerformed
        // TODO add your handling code here:
        NuevosIntegrantes("Esposa", 1);
    }//GEN-LAST:event_BotonEsposaActionPerformed

    private void BotonAbueloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonAbueloActionPerformed
        // TODO add your handling code here:
        NuevosIntegrantes("Abuelos", 4);
    }//GEN-LAST:event_BotonAbueloActionPerformed

    private void BotonGenerarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonGenerarActionPerformed
        saltar();
        
    }//GEN-LAST:event_BotonGenerarActionPerformed

    private void jPanel2MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel2MouseMoved

    private void CampoPersonaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CampoPersonaMouseClicked
        // TODO add your handling code here:
        CampoPersona.setText("");
        CampoPersona.setEditable(true);
    }//GEN-LAST:event_CampoPersonaMouseClicked

    private void botonIngresarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonIngresarMouseClicked
        if (validarCampos(CampoPersona.getText(), "Ingrese su nombre")) {
            usuario.setNombre(CampoPersona.getText());
            configuracion();
            jPanel2.setVisible(false);
            jPanel3.setVisible(true);
        }
    }//GEN-LAST:event_botonIngresarMouseClicked

    private void BotonGenerarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BotonGenerarMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_BotonGenerarMouseClicked

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
            java.util.logging.Logger.getLogger(InterfazMenu.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InterfazMenu.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InterfazMenu.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InterfazMenu.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InterfazMenu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BotonAbuelo;
    private javax.swing.JButton BotonEsposa;
    private javax.swing.JButton BotonGenerar;
    private javax.swing.JButton BotonHermano;
    private javax.swing.JButton BotonHijos;
    private javax.swing.JButton BotonIncorporar;
    private javax.swing.JButton BotonVolverMenu;
    private javax.swing.JTextField CampoPersona;
    private javax.swing.JButton botonIngresar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    // End of variables declaration//GEN-END:variables
}
