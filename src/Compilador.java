
import com.formdev.flatlaf.FlatIntelliJLaf;
import compilerTools.CodeBlock;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import compilerTools.Directory;
import compilerTools.ErrorLSSL;
import compilerTools.Functions;
import compilerTools.Grammar;
import compilerTools.Production;
import compilerTools.TextColor;
import compilerTools.Token;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;

public class Compilador extends javax.swing.JFrame {

    private String title;
    private Directory directorio;
    private ArrayList<Token> tokens;
    private ArrayList<ErrorLSSL> errors;
    private ArrayList<TextColor> textsColor;
    private Timer timerKeyReleased;
    private ArrayList<Production> identProd;
    private HashMap<String, String> identificadores;
    private boolean codeHasBeenCompiled = false;

    // Crear un HashMap para almacenar los símbolos y sus valores
    HashMap<String, Symbol> symbolTable = new HashMap<>();
    static String lexemaClase = "global";

    // Crear un HashMap para almacenar los ints
    HashMap<String, String> lexemaIdentificadorMap = new HashMap<>();

    /**
     * Creates new form Compilador
     */
    public Compilador() {
        initComponents();
        init();
    }

    private void init() {
        title = "Compiler";
        setLocationRelativeTo(null);
        setTitle(title);
        directorio = new Directory(this, jtpCode, title, ".txt");
        addWindowListener(new WindowAdapter() {// Cuando presiona la "X" de la esquina superior derecha
            @Override
            public void windowClosing(WindowEvent e) {
                directorio.Exit();
                System.exit(0);
            }
        });
        Functions.setLineNumberOnJTextComponent(jtpCode);
        timerKeyReleased = new Timer((int) (1000 * 0.3), (ActionEvent e) -> {
            timerKeyReleased.stop();
            colorAnalysis();
        });
        Functions.insertAsteriskInName(this, jtpCode, () -> {
            timerKeyReleased.restart();
        });
        tokens = new ArrayList<>();
        errors = new ArrayList<>();
        textsColor = new ArrayList<>();
        identProd = new ArrayList<>();
        identificadores = new HashMap<>();
        Functions.setAutocompleterJTextComponent(new String[]{}, jtpCode, () -> {
            timerKeyReleased.restart();
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        rootPanel = new javax.swing.JPanel();
        buttonsFilePanel = new javax.swing.JPanel();
        btnAbrir = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnGuardarC = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtpCode = new javax.swing.JTextPane();
        panelButtonCompilerExecute = new javax.swing.JPanel();
        btnCompilar = new javax.swing.JButton();
        btnEjecutar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtaOutputConsole = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblTokens = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblDirecciones = new javax.swing.JTable();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblSimbolos = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        btnAbrir.setText("Abrir");
        btnAbrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAbrirActionPerformed(evt);
            }
        });

        btnNuevo.setText("Nuevo");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnGuardarC.setText("Guardar como");
        btnGuardarC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarCActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout buttonsFilePanelLayout = new javax.swing.GroupLayout(buttonsFilePanel);
        buttonsFilePanel.setLayout(buttonsFilePanelLayout);
        buttonsFilePanelLayout.setHorizontalGroup(
            buttonsFilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(buttonsFilePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnNuevo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAbrir)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnGuardar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnGuardarC)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        buttonsFilePanelLayout.setVerticalGroup(
            buttonsFilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(buttonsFilePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(buttonsFilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAbrir)
                    .addComponent(btnNuevo)
                    .addComponent(btnGuardar)
                    .addComponent(btnGuardarC))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(jtpCode);

        btnCompilar.setText("Compilar");
        btnCompilar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCompilarActionPerformed(evt);
            }
        });

        btnEjecutar.setText("Ejecutar");
        btnEjecutar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEjecutarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelButtonCompilerExecuteLayout = new javax.swing.GroupLayout(panelButtonCompilerExecute);
        panelButtonCompilerExecute.setLayout(panelButtonCompilerExecuteLayout);
        panelButtonCompilerExecuteLayout.setHorizontalGroup(
            panelButtonCompilerExecuteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelButtonCompilerExecuteLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCompilar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEjecutar)
                .addContainerGap())
        );
        panelButtonCompilerExecuteLayout.setVerticalGroup(
            panelButtonCompilerExecuteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelButtonCompilerExecuteLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelButtonCompilerExecuteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCompilar)
                    .addComponent(btnEjecutar))
                .addContainerGap(7, Short.MAX_VALUE))
        );

        jtaOutputConsole.setEditable(false);
        jtaOutputConsole.setBackground(new java.awt.Color(255, 255, 255));
        jtaOutputConsole.setColumns(20);
        jtaOutputConsole.setRows(5);
        jScrollPane2.setViewportView(jtaOutputConsole);

        tblTokens.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Componente léxico", "Lexema", "Identificador", "[No.Línea, Columna]"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblTokens.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(tblTokens);

        tblDirecciones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Token", "No. Línea", "VCI"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblDirecciones.getTableHeader().setReorderingAllowed(false);
        jScrollPane4.setViewportView(tblDirecciones);

        tblSimbolos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Token", "Valor", "D1", "D2", "Ptr", "ambito"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblSimbolos.getTableHeader().setReorderingAllowed(false);
        jScrollPane5.setViewportView(tblSimbolos);

        javax.swing.GroupLayout rootPanelLayout = new javax.swing.GroupLayout(rootPanel);
        rootPanel.setLayout(rootPanelLayout);
        rootPanelLayout.setHorizontalGroup(
            rootPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rootPanelLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(rootPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, rootPanelLayout.createSequentialGroup()
                        .addComponent(buttonsFilePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(panelButtonCompilerExecute, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 693, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 693, Short.MAX_VALUE)
                    .addComponent(jScrollPane5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(rootPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        rootPanelLayout.setVerticalGroup(
            rootPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rootPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(rootPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonsFilePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelButtonCompilerExecute, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(rootPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(rootPanelLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(rootPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        getContentPane().add(rootPanel);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        directorio.New();
        clearFields();
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnAbrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAbrirActionPerformed
        if (directorio.Open()) {
            colorAnalysis();
            clearFields();
        }
    }//GEN-LAST:event_btnAbrirActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if (directorio.Save()) {
            clearFields();
        }
        limpiarTablas(tblSimbolos);
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnGuardarCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarCActionPerformed
        if (directorio.SaveAs()) {
            clearFields();
        }
    }//GEN-LAST:event_btnGuardarCActionPerformed

    private void btnCompilarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCompilarActionPerformed
        lexemaClase = "global";
        lexemaIdentificadorMap.clear();
        symbolTable.clear();
        limpiarTablas(tblTokens);
        limpiarTablas(tblSimbolos);
        limpiarTablas(tblDirecciones);
        if (getTitle().contains("*") || getTitle().equals(title)) {
            if (directorio.Save()) {
                compile();
            }
        } else {
            compile();
        }
    }//GEN-LAST:event_btnCompilarActionPerformed

    private void btnEjecutarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEjecutarActionPerformed
        btnCompilar.doClick();
        if (codeHasBeenCompiled) {
            if (!errors.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No se puede ejecutar el código ya que se encontró uno o más errores",
                        "Error en la compilación", JOptionPane.ERROR_MESSAGE);
            } else {
                CodeBlock codeBlock = Functions.splitCodeInCodeBlocks(tokens, "{", "}", ";");
                System.out.println(codeBlock);
                ArrayList<String> blocksOfCode = codeBlock.getBlocksOfCodeInOrderOfExec();
                System.out.println(blocksOfCode);

            }
        }
    }//GEN-LAST:event_btnEjecutarActionPerformed

    private void compile() {
        clearFields();
        lexicalAnalysis();
        fillTableTokens();
//        Map<String, Object[]> map = new HashMap<>();
//       jTableToMap.crearMapa(tblTokens, map);
        syntacticAnalysis();
        semanticAnalysis();
//        printConsole();
        codeHasBeenCompiled = true;
    }

    private void lexicalAnalysis() {
        // Extraer tokens
        Lexer lexer;
        try {
            File codigo = new File("code.encrypter");
            FileOutputStream output = new FileOutputStream(codigo);
            byte[] bytesText = jtpCode.getText().getBytes();
            output.write(bytesText);
            BufferedReader entrada = new BufferedReader(new InputStreamReader(new FileInputStream(codigo), "UTF8"));
            lexer = new Lexer(entrada);
            while (true) {
                Token token = lexer.yylex();
                if (token == null) {
                    break;
                }
                tokens.add(token);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("El archivo no pudo ser encontrado... " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("Error al escribir en el archivo... " + ex.getMessage());
        }
    }

    private void syntacticAnalysis() {
        Grammar gramatica = new Grammar(tokens, errors);

        /* Mostrar gramáticas */
        //Sirve para eliminar los errores
        gramatica.delete(new String[]{"ERROR", "ERROR2"}, 1);

        /*Agrupacion de valores numericos*/
        gramatica.group("NUM_VAL", "-61|-62|-63", true);

        gramatica.show();

    }

    private void semanticAnalysis() {
        obtenerNombreClase(tblTokens);
        obtenerValores(tblTokens);
//        asignarValoresInt(tblTokens);
    }

    private void obtenerNombreClase(JTable tabla) {
        // Iterar sobre las filas de la JTable
        for (int i = 0; i < tabla.getRowCount(); i++) {
            String lexema = (String) tabla.getValueAt(i, 1); // Obtener el lexema de la fila actual
            if (lexema.equals("public")) {
                // Verificar si el siguiente row existe
                if (i + 1 < tabla.getRowCount()) {
                    // Obtener el lexema del siguiente row
                    lexemaClase = (String) tabla.getValueAt(i + 1, 1);
                    // Verificar si el lexema del siguiente row es "class"
                    if (lexemaClase.equals("class")) {
                        // Guardar el valor del lexema del siguiente row en la variable global
                        lexemaClase = (String) tabla.getValueAt(i + 2, 1);
                        // Realizar cualquier acción necesaria con el lexema guardado
                        System.out.println("El lexema siguiente es: " + lexemaClase);
                        // Puedes salir del bucle si solo necesitas encontrar la primera coincidencia
                        break;
                    }
                }
            }
        }
    }

    private void obtenerValores(JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();

        // Supongamos que tus columnas son Token, Lexema e Identificador
        int lexemaColumnIndex = 1;

        // Crear un HashSet para almacenar los identificadores y verificar duplicados
        HashSet<String> identificadores = new HashSet<>();
        // Recorrer las filas de la tabla
        for (int row = 0; row < model.getRowCount(); row++) {
            String lexema = (String) model.getValueAt(row, lexemaColumnIndex);

            // Si el lexema es "int", procesar el identificador siguiente
            if ("int".equals(lexema)) {
                String busquedaIgual = (String) model.getValueAt(row + 2, lexemaColumnIndex);

                if ("=".equals(busquedaIgual)) {
                    String identificador = (String) model.getValueAt(row + 1, lexemaColumnIndex);
                    if (identificadores.contains(identificador)) {
                        System.err.println("Error: No se pueden duplicar valores.");
                        jtaOutputConsole.setText("Error: No se pueden duplicar valores.");
                        return; // Salir del método si se encuentra un duplicado
                    }
                    String valorAsignar = (String) model.getValueAt(row + 3, lexemaColumnIndex);
                    String pc = (String) model.getValueAt(row + 4, lexemaColumnIndex);
                    System.out.println("HAY IGUAL");
                    String valor = model.getValueAt(row + 1, 0).toString();
                    if (!";".equals(pc)) {
                        System.err.println("Error: Falta un ';' para declarar variables.");
                        jtaOutputConsole.setText("Error: Falta un ';' para declarar variables.");
                        return; // Salir del método si falta ';'  
                    }
                    identificadores.add(identificador);
                    symbolTable.put(identificador, new Symbol(identificador, valor, "0", "0", "null", lexemaClase, "int", Integer.parseInt(valorAsignar)));
                } else {
                    Stack<String> identificadoresStack = new Stack<>();
                    Stack<String> valoresStack = new Stack<>();

                    int rowIndex = row + 1;
                    boolean foundSemicolon = false; // Variable para controlar si se encuentra ';'
                    while (rowIndex < model.getRowCount()) {
                        String identificador = (String) model.getValueAt(rowIndex, lexemaColumnIndex);
                        if (identificador.equals(";")) {
                            foundSemicolon = true;
                            break; // Salir del bucle al encontrar ";"
                        }
                        if (identificadores.contains(identificador)) {
                            System.err.println("Error: No se pueden duplicar valores.");
                            jtaOutputConsole.setText("Error: No se pueden duplicar valores.");
                            return; // Salir del método si se encuentra un duplicado
                        }
                        if (identificador.contains(",")) {
                            rowIndex++;
                            continue; // Si el identificador contiene ",", pasar a la siguiente fila
                        }
                        identificadores.add(identificador);
                        identificadoresStack.push(identificador);

                        String valor = model.getValueAt(rowIndex, 0).toString();
                        valoresStack.push(valor);

                        System.out.println("Identificador en pila: " + identificador);
                        System.out.println("Valor en pila: " + valor);

                        if (!valor.equals("-76") && !valor.equals("-82")) {
                            System.err.println("Error: Falta un ';' para declarar variables.");
                            jtaOutputConsole.setText("Error: Falta un ';' para declarar variables.");
                            return; // Salir del método si falta ';'
                        }

                        rowIndex++; // Pasar a la siguiente fila
                    }

                    if (!foundSemicolon) {
                        System.err.println("Error: Falta un ';' para declarar variables.");
                        jtaOutputConsole.setText("Error: Falta un ';' para declarar variables.");
                        return; // Salir del método si no se encuentra ';'
                    }

                    // Procesar los identificadores y valores almacenados en las pilas
                    while (!identificadoresStack.isEmpty() && !valoresStack.isEmpty()) {
                        String identificador = identificadoresStack.pop();
                        String valor = valoresStack.pop();
                        symbolTable.put(identificador, new Symbol(identificador, valor, "0", "0", "null", lexemaClase, "int", 0));
                    }

                    row = rowIndex - 1; // Actualizar el índice de fila procesada
                }
            } // Si el lexema es "double", procesar el identificador siguiente-------------------------------------------------------------
            else if ("double".equals(lexema)) {
                String busquedaIgual = (String) model.getValueAt(row + 2, lexemaColumnIndex);
                if ("=".equals(busquedaIgual)) {
                    String identificador = (String) model.getValueAt(row + 1, lexemaColumnIndex);
                    if (identificadores.contains(identificador)) {
                        System.err.println("Error: No se pueden duplicar valores.");
                        jtaOutputConsole.setText("Error: No se pueden duplicar valores.");
                        return; // Salir del método si se encuentra un duplicado
                    }
                    String valorAsignar = (String) model.getValueAt(row + 3, lexemaColumnIndex);
                    String pc = (String) model.getValueAt(row + 4, lexemaColumnIndex);
                    System.out.println("HAY IGUAL");
                    String valor = model.getValueAt(row + 1, 0).toString();
                    if (!";".equals(pc)) {
                        System.err.println("Error: Falta un ';' para declarar variables.");
                        jtaOutputConsole.setText("Error: Falta un ';' para declarar variables.");
                        return; // Salir del método si falta ';'  
                    }
                    identificadores.add(identificador);
                    symbolTable.put(identificador, new Symbol(identificador, valor, "0.0", "0", "0", "null", lexemaClase, Double.parseDouble(valorAsignar), "double"));
                } else {
                    Stack<String> identificadoresStack = new Stack<>();
                    Stack<String> valoresStack = new Stack<>();

                    int rowIndex = row + 1;
                    boolean foundSemicolon = false; // Variable para controlar si se encuentra ';'
                    while (rowIndex < model.getRowCount()) {
                        String identificador = (String) model.getValueAt(rowIndex, lexemaColumnIndex);
                        if (identificador.equals(";")) {
                            foundSemicolon = true;
                            break; // Salir del bucle al encontrar ";"
                        }
                        if (identificadores.contains(identificador)) {
                            System.err.println("Error: No se pueden duplicar valores.");
                            jtaOutputConsole.setText("Error: No se pueden duplicar valores.");
                            return; // Salir del método si se encuentra un duplicado
                        }
                        if (identificador.contains(",")) {
                            rowIndex++;
                            continue; // Si el identificador contiene ",", pasar a la siguiente fila
                        }
                        identificadores.add(identificador);
                        identificadoresStack.push(identificador);

                        String valor = model.getValueAt(rowIndex, 0).toString();
                        valoresStack.push(valor);

                        System.out.println("Identificador en pila: " + identificador);
                        System.out.println("Valor en pila: " + valor);

                        if (!valor.equals("-76") && !valor.equals("-82")) {
                            System.err.println("Error: Falta un ';' para declarar variables.");
                            jtaOutputConsole.setText("Error: Falta un ';' para declarar variables.");
                            return; // Salir del método si falta ';'
                        }

                        rowIndex++; // Pasar a la siguiente fila
                    }

                    if (!foundSemicolon) {
                        System.err.println("Error: Falta un ';' para declarar variables.");
                        jtaOutputConsole.setText("Error: Falta un ';' para declarar variables.");
                        return; // Salir del método si no se encuentra ';'
                    }

                    // Procesar los identificadores y valores almacenados en las pilas
                    while (!identificadoresStack.isEmpty() && !valoresStack.isEmpty()) {
                        String identificador = identificadoresStack.pop();
                        String valor = valoresStack.pop();
                        symbolTable.put(identificador, new Symbol(identificador, valor, "0.0", "0", "0", "null", lexemaClase, 0.0, "double"));
                    }

                    row = rowIndex - 1; // Actualizar el índice de fila procesada
                }
            }
        }

        // Imprimir la tabla de símbolos
        System.out.println("Lexemas e Identificadores tabla simbolos:");
        for (Symbol symbol : symbolTable.values()) {
            System.out.println(symbol);
        }

        // Llenar la tabla con los datos del HashMap
        DefaultTableModel tabladeSimbolos = (DefaultTableModel) tblSimbolos.getModel();
        for (Symbol symbol : symbolTable.values()) {
            if ("int".equals(symbol.getTipoDato())) {
                tabladeSimbolos.addRow(new Object[]{
                    symbol.getID(),
                    symbol.getToken(),
                    symbol.getValorInt(),
                    symbol.getD1(),
                    symbol.getD2(),
                    symbol.getPtr(),
                    symbol.getAmbito()
                });
            } else if ("double".equals(symbol.getTipoDato())) {
                tabladeSimbolos.addRow(new Object[]{
                    symbol.getID(),
                    symbol.getToken(),
                    symbol.getValorDouble(),
                    symbol.getD1(),
                    symbol.getD2(),
                    symbol.getPtr(),
                    symbol.getAmbito()
                });
            }
        }
    }

    private void asignarValoresInt(JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();

        // Columna identificador para sacar el -2
        int lexemaColumnIndex = 2;

        // Columna identificador para sacar el lexema
        int lexemaReal = 1;

// Recorrer las filas de la tabla
        for (int row = 0; row < model.getRowCount() - 2; row++) { // Resta 2 para evitar el índice fuera de límites
            // Asegurarse de que row - 1 no sea negativo
            String verificaNoClase = row > 0 ? (String) model.getValueAt(row - 1, lexemaReal) : null;

            Integer lexema = (Integer) model.getValueAt(row, lexemaColumnIndex);
            String variable = (String) model.getValueAt(row, lexemaReal);
            String igual = (String) model.getValueAt(row + 1, lexemaReal);
            String valor = (String) model.getValueAt(row + 2, lexemaReal);

            System.out.println("La variable es: " + variable);
            System.out.println("El identificador es: " + lexema);
            System.out.println("El igual es: " + igual);
            System.out.println("El valor es: " + valor);

            // Asegúrate de que los índices están dentro de los límites
            if ("-2".equals(lexema.toString()) && row + 2 < model.getRowCount()) {
                String identificador = (String) model.getValueAt(row, lexemaReal);
                // Verificar si el identificador ya existe en el HashMap
                if (symbolTable.containsKey(identificador)) {
                    if ("=".equals(igual)) {
                        try {
                            symbolTable.put(identificador, new Symbol(identificador, model.getValueAt(row, 0).toString(), "0", "0", "null", lexemaClase, "int", Integer.parseInt(valor)));
                        } catch (NumberFormatException e) {
                            System.err.println("Error: Valor no válido para la variable " + identificador);
                            jtaOutputConsole.setText("Error: Valor no válido para la variable " + identificador);
                            return; // Salir del método si ocurre un error en la conversión
                        }
                    }
                } else if (!(symbolTable.containsKey(identificador))) {
                    if (verificaNoClase != null && verificaNoClase.contains("class")) {
                        row++;
                    } else {
                        System.err.println("Error: No se pueden asignar valores a variables no declaradas.");
                        jtaOutputConsole.setText("Error: No se pueden asignar valores a variables no declaradas.");
                        return; // Salir del método si se encuentra un duplicado
                    }
                }

            }
            // Si el lexema es ";", termina el proceso
            if (";".equals(lexema)) {
                break;
            }
        }

        // Imprimir el HashMap tabla simbolos
        System.out.println("Lexemas e Identificadores tabla simbolos:");
        for (String lexema : symbolTable.keySet()) {
            System.out.println("Lexema: " + lexema + ", Identificador: " + symbolTable.get(lexema));
        }

        // Llenar la tabla con los datos del HashMap
        limpiarTablas(tblSimbolos);
        DefaultTableModel tabladeSimbolos = (DefaultTableModel) tblSimbolos.getModel();
        for (Symbol symbol : symbolTable.values()) {
            tabladeSimbolos.addRow(new Object[]{
                symbol.getID(),
                symbol.getToken(),
                symbol.getValorInt(),
                symbol.getD1(),
                symbol.getD2(),
                symbol.getPtr(),
                symbol.getAmbito()
            });
        }
    }

    private void asignarValoresDirectosInt(JTable table, int row) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        String identificador = (String) model.getValueAt(row + 1, 1);
        String igual = (String) model.getValueAt(row + 2, 1);
        String valor = (String) model.getValueAt(row + 3, 1);
        String puncom = (String) model.getValueAt(row + 4, 1);

        symbolTable.put(identificador, new Symbol(identificador, model.getValueAt(row, 0).toString(), "0", "0", "null", lexemaClase, "int", Integer.parseInt(valor)));

        // Imprimir el HashMap tabla simbolos
        System.out.println("Lexemas e Identificadores tabla simbolos:");
        for (String lexema : symbolTable.keySet()) {
            System.out.println("Lexema: " + lexema + ", Identificador: " + symbolTable.get(lexema));
        }

        // Llenar la tabla con los datos del HashMap
        limpiarTablas(tblSimbolos);
        DefaultTableModel tabladeSimbolos = (DefaultTableModel) tblSimbolos.getModel();
        for (Symbol symbol : symbolTable.values()) {
            tabladeSimbolos.addRow(new Object[]{
                symbol.getID(),
                symbol.getToken(),
                symbol.getValorInt(),
                symbol.getD1(),
                symbol.getD2(),
                symbol.getPtr(),
                symbol.getAmbito()
            });
        }
    }

    private void limpiarTablas(JTable table) {
        // Suponiendo que tienes una JTable llamada "table"
        DefaultTableModel model = (DefaultTableModel) table.getModel(); // Obtener el modelo de la JTable

        while (model.getRowCount() > 0) { // Mientras haya filas en la tabla
            model.removeRow(0); // Eliminar la primera fila (índice 0)
        }
    }

    private void colorAnalysis() {
        /* Limpiar el arreglo de colores */
        textsColor.clear();
        /* Extraer rangos de colores */
        LexerColor lexerColor;
        try {
            File codigo = new File("color.encrypter");
            FileOutputStream output = new FileOutputStream(codigo);
            byte[] bytesText = jtpCode.getText().getBytes();
            output.write(bytesText);
            BufferedReader entrada = new BufferedReader(new InputStreamReader(new FileInputStream(codigo), "UTF8"));
            lexerColor = new LexerColor(entrada);
            while (true) {
                TextColor textColor = lexerColor.yylex();
                if (textColor == null) {
                    break;
                }
                textsColor.add(textColor);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("El archivo no pudo ser encontrado... " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("Error al escribir en el archivo... " + ex.getMessage());
        }
        Functions.colorTextPane(textsColor, jtpCode, new Color(40, 40, 40));
    }

    /*private void fillTableTokens() {
    tokens.forEach(token -> {
    boolean isIdentifier = isIdentifier(token);
    if(isIdentifier == true){
    return -2;
    }
    Object[] data = new Object[]{token.getLexicalComp(), token.getLexeme(),isIdentifier,"[" + token.getLine() + ", " + token.getColumn() + "]"};
    Functions.addRowDataInTable(tblTokens, data);
    });
    }*/
    private void fillTableTokens() {
        //ArrayList<Token> identifiers = new ArrayList<>(); // Lista para almacenar identificadores

        tokens.forEach(token -> {
            boolean isIdentifier = isIdentifier(token);
            int identifierValue = isIdentifier ? -2 : -1;
            if (isIdentifier) {
                Object[] data = new Object[]{token.getLexicalComp(), token.getLexeme(), identifierValue, "[" + token.getLine() + ", " + token.getColumn() + "]"};
                Functions.addRowDataInTable(tblTokens, data);
            } else {
                Object[] data = new Object[]{token.getLexicalComp(), token.getLexeme(), identifierValue, "[" + token.getLine() + ", " + token.getColumn() + "]"};
                Functions.addRowDataInTable(tblTokens, data);
            }
        });

    }

    private boolean isIdentifier(Token token) {
        String lexema = token.getLexeme();

        // Lista de palabras reservadas
        String[] palabrasReservadas = {"int", "String", "public", "double", "while", "if", "print", "class" /* Agrega más palabras reservadas aquí */};

        // Verificar si el lexema comienza con una letra o un guión bajo, seguido de letras, dígitos o guiones bajos
        if (lexema.matches("[a-zA-Z_][a-zA-Z0-9_]*")) {
            // Verificar si el lexema no es una palabra reservada
            for (String palabraReservada : palabrasReservadas) {
                if (lexema.equals(palabraReservada)) {
                    return false; // No es un identificador, es una palabra reservada
                }
            }
            return true; // Es un identificador válido
        } else {
            return false; // No cumple con el formato de identificador
        }
    }

    private void printConsole() {
        int sizeErrors = errors.size();
        if (sizeErrors > 0) {
            Functions.sortErrorsByLineAndColumn(errors);
            String strErrors = "\n";
            for (ErrorLSSL error : errors) {
                String strError = String.valueOf(error);
                strErrors += strError + "\n";
            }
            jtaOutputConsole.setText("Compilación terminada...\n" + strErrors + "\nLa compilación terminó con errores...");
        } else {
            jtaOutputConsole.setText("Compilación terminada...");
        }
        jtaOutputConsole.setCaretPosition(0);
    }

    private void clearFields() {
        Functions.clearDataInTable(tblTokens);
        jtaOutputConsole.setText("");
        tokens.clear();
        errors.clear();
        identProd.clear();
        identificadores.clear();
        codeHasBeenCompiled = false;
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
            java.util.logging.Logger.getLogger(Compilador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Compilador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Compilador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Compilador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(new FlatIntelliJLaf());
            } catch (UnsupportedLookAndFeelException ex) {
                System.out.println("LookAndFeel no soportado: " + ex);
            }
            new Compilador().setVisible(true);
        });

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAbrir;
    private javax.swing.JButton btnCompilar;
    private javax.swing.JButton btnEjecutar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnGuardarC;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JPanel buttonsFilePanel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTextArea jtaOutputConsole;
    private javax.swing.JTextPane jtpCode;
    private javax.swing.JPanel panelButtonCompilerExecute;
    private javax.swing.JPanel rootPanel;
    private javax.swing.JTable tblDirecciones;
    private javax.swing.JTable tblSimbolos;
    private javax.swing.JTable tblTokens;
    // End of variables declaration//GEN-END:variables
}
