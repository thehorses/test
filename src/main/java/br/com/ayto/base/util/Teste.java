package br.com.ayto.base.util;

import java.util.Scanner;

public class Teste {

	// public static void main1(String[] args) {
	// Scanner sc = null;
	// try {
	// sc = new Scanner(System.in);
	// int qtdePedras = sc.nextInt();
	//
	// Map<Character, Integer> map = new HashMap<Character, Integer>();
	// for (int i = 1; i <= qtdePedras; i++) {
	// String pedra = sc.next();
	// Set<Character> elems = new HashSet<Character>();
	// for (int p = 0; p < pedra.length(); p++) {
	// Character elem = pedra.toCharArray()[p];
	// if (!elems.contains(elem)) {
	// map.put(elem, map.get(elem) == null ? 1 : map.get(elem) + 1);
	// elems.add(elem);
	// }
	// }
	// }
	//
	// int saida = 0;
	// for (Entry<Character, Integer> entry : map.entrySet()) {
	// if (entry.getValue() == qtdePedras) {
	// saida++;
	// }
	// }
	//
	// System.out.println(saida);
	// } finally {
	// try {
	// sc.close();
	// } catch (Exception e) {
	// }
	// }
	// }

	public static void main(String[] args) {
		Scanner sc = null;
		try {
			sc = new Scanner(System.in);
			//int qtde = sc.nextInt();
			int qtde = Integer.parseInt(args[0]);
			
			for (int i = 0; i < qtde; i++) {
				int raizPerf = 0;
				
				//int de = sc.nextInt();
				//int ate = sc.nextInt();
				int de = Integer.parseInt(args[1]);
				int ate = Integer.parseInt(args[2]);
				for (int n = de; n <= ate; n++) {
					int valor = 1;
					while (valor <= n) {
						if (valor * valor == n) {
							raizPerf++;
							System.out.println("qtde:" + raizPerf + " raiz de " + n +" = " + valor);
							break;
						}
						valor++;
					}
				}
				System.out.println(raizPerf);
			}

		} finally {
			try {
				sc.close();
			} catch (Exception e) {
			}
		}
	}

	public static void main22(String[] args) {
		Scanner sc = null;
		try {
			sc = new Scanner(System.in);
			int qtde = sc.nextInt();

			for (int i = 1; i <= qtde; i++) {
				int valor = 0;
				String s = sc.next();
				for1: for (int n = 0; n < s.length(); n++) {
					String s2 = s.substring(n);
					for (int p = 0; p < s.length() && p < s2.length(); p++) {
						if (s.toCharArray()[p] == s2.toCharArray()[p]) {
							valor++;
						} else {
							continue for1;
						}

					}
				}
				System.out.println(valor);

			}

		} finally {
			try {
				sc.close();
			} catch (Exception e) {
			}
		}
	}

	public static void main2(String[] args) {
		Scanner sc = null;
		try {
			sc = new Scanner(System.in);
			// int qtde = sc.nextInt();
			int qtde = Integer.parseInt(args[0]);

			for (int i = 1; i <= qtde; i++) {
				int valor = 0;
				// String s = sc.next();

				String s = args[i];
				for1: for (int n = 0; n < s.length(); n++) {
					String s2 = s.substring(n);
					for (int p = 0; p < s2.length(); p++) {
						if (s.toCharArray()[p] == s2.toCharArray()[p]) {
							valor++;
						} else {
							continue for1;
						}

					}
				}
				System.out.println(valor);

			}

		} finally {
			try {
				sc.close();
			} catch (Exception e) {
			}
		}
	}
}
