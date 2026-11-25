import com.sun.jna.Library;
import com.sun.jna.Native;
import java.util.Scanner;

public class labirintoTrabalho2 {

    // Matriz 40x25 (bem maior)
    // 0 = espaço vazio | 1 = parede | 2 = bonequinho | 3 = frutinha | 4 = início | 5 = saída
    static int[][] Labirinto = {
            { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
            { 4, 0, 3, 0, 0, 0, 1, 0, 0, 0, 3, 0, 0, 0, 1, 0, 0, 0, 3, 0, 0, 0, 1, 0, 0, 0, 3, 0, 0, 0, 1, 0, 0, 0, 3, 0, 0, 0, 0, 1 },
            { 1, 1, 0, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 0, 1 },
            { 1, 0, 0, 3, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 3, 1, 0, 1 },
            { 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1 },
            { 1, 0, 0, 0, 0, 0, 3, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 3, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1 },
            { 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1 },
            { 1, 0, 3, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 3, 0, 0, 0, 1, 0, 0, 0, 0, 3, 0, 1 },
            { 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 0, 1 },
            { 1, 0, 1, 0, 0, 0, 0, 0, 3, 0, 0, 0, 1, 0, 0, 0, 0, 0, 3, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 1 },
            { 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1 },
            { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 3, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1 },
            { 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1 },
            { 1, 0, 3, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 3, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 1, 0, 0, 0, 1, 0, 1 },
            { 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1, 0, 1 },
            { 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 3, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 3, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 1 },
            { 1, 0, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1 },
            { 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 3, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 3, 0, 0, 1, 0, 0, 0, 0, 0, 1 },
            { 1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1 },
            { 1, 0, 3, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 3, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 3, 1 },
            { 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1 },
            { 1, 0, 0, 0, 3, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 3, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 3, 0, 1, 0, 0, 0, 1 },
            { 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1 },
            { 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 5, 1 },
            { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }
    };

    static final int LINHA_INICIO_LABIRINTO = 3;
    static final int TEMPO_LIMITE = 60;

    // Liga o Java às funções _kbhit e _getch do Windows (só usada no Windows)
    public interface Crt extends Library {
        Crt INSTANCE = Native.load("msvcrt", Crt.class);

        int _kbhit();

        int _getch();
    }

    static boolean windows = System.getProperty("os.name").toLowerCase().contains("win");
    static boolean tecladoPreparado = false;

    public static boolean pressionouTecla() {
        if (windows) {
            return Crt.INSTANCE._kbhit() != 0;
        }
        prepararTecladoMacLinux();
        try {
            return System.in.available() > 0;
        } catch (Exception e) {
            return false;
        }
    }

    public static int obtemTeclaPressionada() {
        if (windows) {
            return Crt.INSTANCE._getch();
        }
        prepararTecladoMacLinux();
        try {
            return System.in.read();
        } catch (Exception e) {
            return -1;
        }
    }

    public static void esperarTecla() {
        System.out.println("Pressione uma tecla para continuar...");
        obtemTeclaPressionada();
    }

    static void prepararTecladoMacLinux() {
        if (tecladoPreparado) {
            return;
        }
        tecladoPreparado = true;
        executarStty("-icanon -echo -icrnl min 1");
        Runtime.getRuntime().addShutdownHook(new Thread(() -> executarStty("sane")));
    }

    static void executarStty(String opcoes) {
        try {
            new ProcessBuilder("sh", "-c", "stty " + opcoes + " < /dev/tty").start().waitFor();
        } catch (Exception e) {
            // se não conseguir, o teclado só vai funcionar com ENTER
        }
    }
    // ===================================================================

    // ===================================================================
    // CRONÔMETRO
    //
    // Funções para usar no jogo:
    //   iniciarCronometro() -> começa a contar o tempo do zero
    //   segundosPassados()  -> quantos segundos passaram desde o início
    // ===================================================================

    // Guarda o momento em que o cronômetro começou (em milissegundos)
    static long inicioCronometro = 0;

    // Começa (ou recomeça) a contar o tempo do zero.
    public static void iniciarCronometro() {
        inicioCronometro = System.currentTimeMillis();
    }

    // Retorna quantos segundos passaram desde o iniciarCronometro().
    // O valor tem casas decimais: 1.5 quer dizer um segundo e meio.
    public static double segundosPassados() {
        long agora = System.currentTimeMillis();
        return (agora - inicioCronometro) / 1000.0;
    }
    // ===================================================================

    public static void limparConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void gotoXY(int linha, int coluna) {
        char escCode = 0x1B;
        System.out.print(String.format("%c[%d;%df", escCode, linha, coluna));
    }

    public static final int PRETO = 0;
    public static final int VERMELHO = 1;
    public static final int VERDE = 2;
    public static final int AMARELO = 3;
    public static final int AZUL = 4;
    public static final int MAGENTA = 5;
    public static final int CIANO = 6;
    public static final int CINZA = 7;
    public static final int BRANCO = 15;
    public static final int COR_PADRAO = -1;

    public static void mudarCor(int cor) {
        if (cor >= 0 && cor <= 255) {
            System.out.print("\033[38;5;" + cor + "m");
        } else {
            System.out.print("\033[39m");
        }
    }

    public static void mudarCorFundo(int cor) {
        if (cor >= 0 && cor <= 255) {
            System.out.print("\033[48;5;" + cor + "m");
        } else {
            System.out.print("\033[49m");
        }
    }

    public static void atualizarMenu(int opcaoSelecionada, String[] opcoes) {
        limparConsole();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("╔═════════════════════╗");
        System.out.println("║    Menu Principal   ║");
        System.out.println("╠═════════════════════╣");
        for (int i = 0; i < opcoes.length; i++) {
            if (i == opcaoSelecionada) {
                System.out.print("║> ");
                mudarCor(VERDE);
                System.out.print(String.format("%-17s", opcoes[i]));
                mudarCor(COR_PADRAO);
                System.out.println("  ║");
            } else {
                System.out.println("║  " + String.format("%-17s", opcoes[i]) + "  ║");
            }
        }
        System.out.println("╚═════════════════════╝");
        System.out.println();
        System.out.println("Use W/S para navegar e ENTER para selecionar. ESC para sair.");
    }

    public static void executarAcaoMenu(int opcaoSelecionada) {
        limparConsole();

        switch (opcaoSelecionada) {
            case 0:
                jogarLabirinto(Labirinto);
                return;
            case 1:
                System.out.println("\n\nCarregar jogo selecionado.\n\n");
                break;
            case 2:
                imprimirMenuAjuda();
                break;
            case 3:
                System.out.println("\n\nAjuda selecionado.\n\n");
                break;
            case 4:
                System.out.println("\n\nSobre selecionado.\n\n");
                break;
            default:
                System.out.println("\n\nOpção inválida.\n\n");
                break;
        }
        esperarTecla();
        limparConsole();
    }

    private static String representacaoCelula(int valor) {
        if (valor == 1) {
            return "\033[38;5;" + CIANO + "m██\033[39m";
        } else if (valor == 2) {
            return "\033[38;5;" + VERDE + "m@@\033[39m";
        } else if (valor == 3) {
            return "\033[38;5;" + AMARELO + "m *\033[39m";
        } else if (valor == 4) {
            return "\033[38;5;" + VERDE + "mSS\033[39m";
        } else if (valor == 5) {
            return "\033[38;5;" + MAGENTA + "mEE\033[39m";
        }
        return "  ";
    }

    private static void desenharCelula(int linha, int coluna, int valor) {
        int telaLinha = LINHA_INICIO_LABIRINTO + linha;
        int telaColuna = (coluna * 2) + 1;
        gotoXY(telaLinha, telaColuna);
        System.out.print(representacaoCelula(valor));
        System.out.flush();
    }

    private static void atualizarPlacar(int frutasComidas, int totalFrutas) {
        gotoXY(2, 1);
        mudarCor(AMARELO);
        System.out.print("Frutinhas: " + frutasComidas + "/" + totalFrutas + "   ");
        mudarCor(COR_PADRAO);
        System.out.flush();
    }

    private static int[][] copiarMatriz(int[][] origem) {
        int[][] copia = new int[origem.length][origem[0].length];
        for (int i = 0; i < origem.length; i++) {
            for (int j = 0; j < origem[i].length; j++) {
                copia[i][j] = origem[i][j];
            }
        }
        return copia;
    }

    private static void perdeu(int[][] matrizOriginal, int[][] matriz) {

        limparConsole();

        
                System.out.println(
                "                            ▄█▄\n" +
                "██╗   ██╗ ██████╗  ██████╗███████╗\n" +
                "██║   ██║██╔═══██╗██╔════╝██╔════╝\n" +
                "██║   ██║██║   ██║██║     █████╗\n" +
                "╚██╗ ██╔╝██║   ██║██║     ██╔══╝\n" +
                " ╚████╔╝ ╚██████╔╝╚██████╗███████╗\n" +
                "  ╚═══╝   ╚═════╝  ╚═════╝╚══════╝\n" +
                "\n" +
                "██████╗ ███████╗██████╗ ██████╗ ███████╗██╗   ██╗\n" +
                "██╔══██╗██╔════╝██╔══██╗██╔══██╗██╔════╝██║   ██║\n" +
                "██████╔╝█████╗  ██████╔╝██║  ██║█████╗  ██║   ██║\n" +
                "██╔═══╝ ██╔══╝  ██╔══██╗██║  ██║██╔══╝  ██║   ██║\n" +
                "██║     ███████╗██║  ██║██████╔╝███████╗╚██████╔╝\n" +
                "╚═╝     ╚══════╝╚═╝  ╚═╝╚═════╝ ╚══════╝ ╚═════╝");
                
        System.out.println();
        System.out.println("VOCÊ PERDEU!");
        System.out.println("Você bateu em uma parede.");

        esperarTecla();

        jogarLabirinto(matrizOriginal);
    }

    public static void jogarLabirinto(int[][] matrizOriginal) {
        int[][] matriz = copiarMatriz(matrizOriginal);
        int linha = -1;
        int coluna = -1;
        int totalFrutas = 0;
        int frutasComidas = 0;

        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                if (matriz[i][j] == 3) {
                    totalFrutas++;
                }
                if (matriz[i][j] == 4 || matriz[i][j] == 2) {
                    linha = i;
                    coluna = j;
                }
            }
        }

        if (linha == -1) {
            linha = 1;
            coluna = 0;
        }

        int valorAnterior = matriz[linha][coluna] == 2 ? 0 : matriz[linha][coluna];
        matriz[linha][coluna] = 2;

        // Iniciar cronômetro com limite de 60 segundos
        iniciarCronometro();

        limparConsole();
        gotoXY(1, 1);
        System.out.println("W/A/S/D para mover | @@ = Você | * = Frutinha | ESC para voltar");
        atualizarPlacar(frutasComidas, totalFrutas);
        System.out.println();

        // Imprime o labirinto ASCII uma única vez
        imprimirLabirinto(matriz);

        while (true) {
            // Verifica se o tempo passou do limite
            if (segundosPassados() > TEMPO_LIMITE) {
                gotoXY(LINHA_INICIO_LABIRINTO + matriz.length + 2, 1);
                System.out.println("Tempo esgotado! Você perdeu.");
                esperarTecla();
                return;
            }

            if (!pressionouTecla()) {
                // Pequena pausa para evitar uso excessivo de CPU
                try { Thread.sleep(50); } catch (InterruptedException e) {}
                continue;
            }

            int ch = obtemTeclaPressionada();
            int novaLinha = linha;
            int novaColuna = coluna;

            if (ch == 27) {
                gotoXY(LINHA_INICIO_LABIRINTO + matriz.length + 2, 1);
                return;
            } else if (ch == 'w' || ch == 'W') {
                novaLinha--;
            } else if (ch == 's' || ch == 'S') {
                novaLinha++;
            } else if (ch == 'a' || ch == 'A') {
                novaColuna--;
            } else if (ch == 'd' || ch == 'D') {
                novaColuna++;
            }

            // Tecla que não move o jogador: ignora
            if (novaLinha == linha && novaColuna == coluna) {
                continue;
            }

            // Se sair do mapa perde
            if (novaLinha < 0 || novaLinha >= matriz.length
                    || novaColuna < 0 || novaColuna >= matriz[0].length) {
                perdeu(matrizOriginal, matriz);
                return;
            }

            // Se bater em parede perde
            if (matriz[novaLinha][novaColuna] == 1) {
                perdeu(matrizOriginal, matriz);
                return;
            }

            int destino = matriz[novaLinha][novaColuna];

            // Se pisar em uma frutinha, come e transforma em espaço vazio
            if (destino == 3) {
                frutasComidas++;
                destino = 0;
                atualizarPlacar(frutasComidas, totalFrutas);
            }

            // Atualiza apenas a célula antiga e a célula nova, sem reimprimir o labirinto
            matriz[linha][coluna] = valorAnterior;
            desenharCelula(linha, coluna, valorAnterior);

            linha = novaLinha;
            coluna = novaColuna;
            valorAnterior = (destino == 4 || destino == 5) ? destino : 0;
            matriz[linha][coluna] = 2;
            desenharCelula(linha, coluna, 2);

            if (destino == 5) {
                limparConsole();
                System.out.println(
                        "██╗   ██╗██╗████████╗ ██████╗ ██████╗ ██╗ █████╗\n" +
                        "██║   ██║██║╚══██╔══╝██╔═══██╗██╔══██╗██║██╔══██╗\n" +
                        "██║   ██║██║   ██║   ██║   ██║██████╔╝██║███████║\n" +
                        "╚██╗ ██╔╝██║   ██║   ██║   ██║██╔══██╗██║██╔══██║\n" +
                        " ╚████╔╝ ██║   ██║   ╚██████╔╝██║  ██║██║██║  ██║\n" +
                        "  ╚═══╝  ╚═╝   ╚═╝    ╚═════╝ ╚═╝  ╚═╝╚═╝╚═╝  ╚═╝");
                System.out.println();
                System.out.println("Você escapou do labirinto!");
                System.out.println("Frutinhas: " + frutasComidas + "/" + totalFrutas);
                System.out.printf("Tempo: %.1f segundos%n", segundosPassados());

                esperarTecla();
                return;
            }
        }
    }

    public static void mostrarLogo() {

        System.out.println(
                "██╗      █████╗ ██████╗ ██╗██████╗ ██╗███╗   ██╗████████╗ ██████╗\n" +
                "██║     ██╔══██╗██╔══██╗██║██╔══██╗██║████╗  ██║╚══██╔══╝██╔═══██╗\n" +
                "██║     ███████║██████╔╝██║██████╔╝██║██╔██╗ ██║   ██║   ██║   ██║\n" +
                "██║     ██╔══██║██╔══██╗██║██╔══██╗██║██║╚██╗██║   ██║   ██║   ██║\n" +
                "███████╗██║  ██║██████╔╝██║██║  ██║██║██║ ╚████║   ██║   ╚██████╔╝\n" +
                "╚══════╝╚═╝  ╚═╝╚═════╝ ╚═╝╚═╝  ╚═╝╚═╝╚═╝  ╚═══╝   ╚═╝    ╚═════╝");
    }

    public static void imprimirMenu() {
        String[] opcoes = {
                "INICIAR JOGO",
                "CARREGAR JOGO",
                "AJUDA"
        };

        int opcaoSelecionada = 0;
        atualizarMenu(opcaoSelecionada, opcoes);

        while (true) {
            if (!pressionouTecla()) {
                // Pequena pausa para evitar uso excessivo de CPU
                try { Thread.sleep(50); } catch (InterruptedException e) {}
                continue;
            }

            int ch = obtemTeclaPressionada();

            switch (ch) {
                case 'w':
                case 'W':
                    opcaoSelecionada--;
                    if (opcaoSelecionada < 0) {
                        opcaoSelecionada = opcoes.length - 1;
                    }
                    atualizarMenu(opcaoSelecionada, opcoes);
                    break;

                case 's':
                case 'S':
                    opcaoSelecionada++;
                    if (opcaoSelecionada >= opcoes.length) {
                        opcaoSelecionada = 0;
                    }
                    atualizarMenu(opcaoSelecionada, opcoes);
                    break;

                case 10:
                case 13:
                    executarAcaoMenu(opcaoSelecionada);
                    atualizarMenu(opcaoSelecionada, opcoes);
                    break;

                case 27:
                    return;
            }
        }
    }

    public static void main(String[] args) {
        imprimirMenu();
        imprimirLabirinto(Labirinto);
        System.out.println("labirinto carregado");
        mostrarLogo();
    }

    private static void addmenu(int x) {
        switch (x) {
            case 1:
                imprimirLabirinto(new int[0][0]);
                break;

            case 2:
                break;

            case 3:
                imprimirMenuAjuda();
                break;
        }
    }

    private static void imprimirMenuAjuda() {
        System.out.println("controles");
        System.out.println("'W' para andar para cima");
        System.out.println("'S' para andar para baixo");
        System.out.println("'A' para andar para esquerda");
        System.out.println("'D' para andar para direita");
    }

    public static void imprimirLabirinto(int[][] matriz) {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                System.out.print(representacaoCelula(matriz[i][j]));
            }
            System.out.println();
        }
    }

    public void setVisible(boolean b) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setVisible'");
    }
}