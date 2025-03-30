import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        AVLTree tree = new AVLTree();

        System.out.println("Insira os comandos para manipular a árvore AVL: ");
        System.out.println("I + numero(Ex: i 45) - Inserção\nR + numero(Ex: R 45) - Remoção\nB + numero(Ex: B 45) - Busca\nMostrar Arvore:\nPOS - Pos Ordem\nPRE - Pre Ordem\nEM - Em Ordem");

        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            String[] command = input.split(" ");

            String operation = command[0].toLowerCase();
            switch (operation) {
                case "i": // Inserção
                    if (command.length > 1) {
                        try{
                            int key = Integer.parseInt(command[1]);
                            tree.insert(key);
                            System.out.println("Árvore após inserção do elemento " + key + ":");
                            tree.inOrder();
                        }catch (NumberFormatException e){
                            System.out.println("Comando inválido, favor digitar um número após selecionar a operação");
                        }catch (Exception e){
                            System.out.println("Desculpe, ocorreu um erro. Tente novamente");
                        }
                    } else {
                        System.out.println("Comando inválido para inserção.");
                    }
                    break;

                case "r": // Remoção
                    if (command.length > 1) {
                        try{
                            int key = Integer.parseInt(command[1]);
                            tree.delete(key);
                            System.out.println("Árvore após remoção do elemento " + key + ":");
                            tree.inOrder();
                        }catch (NumberFormatException e){
                            System.out.println("Comando inválido, favor digitar um número após selecionar a operação");
                        }catch (Exception e){
                            System.out.println("Desculpe, ocorreu um erro. Tente novamente");
                        }
                    } else {
                        System.out.println("Comando inválido para remoção.");
                    }
                    break;

                case "b": // Busca
                    if (command.length > 1) {
                        try{
                            int key = Integer.parseInt(command[1]);
                            System.out.print("Caminho percorrido para buscar o elemento " + key + ": ");
                            boolean found = tree.searchAndPrintPath(key);
                            if (found) {
                                System.out.println("\nElemento " + key + " encontrado na árvore.");
                            } else {
                                System.out.println("\nElemento " + key + " não encontrado na árvore.");
                            }
                        }catch (NumberFormatException e){
                            System.out.println("Comando inválido, favor digitar um número após selecionar a operação");
                        }catch (Exception e){
                            System.out.println("Desculpe, ocorreu um erro. Tente novamente");
                        }
                    } else {
                        System.out.println("Comando inválido para busca.");
                    }
                    break;

                case "pos": // Percurso Pós-Ordem
                    System.out.println("Percurso Pós-Ordem:");
                    tree.postOrder();
                    break;

                case "pre": // Percurso Pré-Ordem
                    System.out.println("Percurso Pré-Ordem:");
                    tree.preOrder();
                    break;

                case "em": // Percurso Em-Ordem
                    System.out.println("Percurso Em-Ordem:");
                    tree.inOrder();
                    break;

                default:
                    System.out.println("Operação desconhecida: " + operation);
            }
        }
        scanner.close();
    }
}