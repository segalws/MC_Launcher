package net.launcher.components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.net.URL;
import java.nio.channels.FileChannel;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import net.minecraft.Launcher;
import net.launcher.run.Settings;
import net.launcher.utils.BaseUtils;
import net.launcher.utils.EncodingUtils;
import net.launcher.utils.GuardUtils;
import net.launcher.utils.java.eURLClassLoader;

public class Game extends JFrame {

	private static final long serialVersionUID = 1L;
	public static Launcher mcapplet;
	private static eURLClassLoader cl;
	static String Cl = null;
	Timer timer = null;
	int i = 0;
	static List<String> params = new ArrayList<String>();

	public Game(final String answer) {

		GuardUtils.getLogs(
				new File(BaseUtils.getAssetsDir().getAbsolutePath() + File.separator + BaseUtils.getClientName()));
		GuardUtils.getLogs(new File(BaseUtils.getAssetsDir().getAbsolutePath() + File.separator + "logs"));
		try {
			Splash(new File(BaseUtils.getAssetsDir().getAbsolutePath() + File.separator + BaseUtils.getClientName()
					+ File.separator + "config"),
					new File(BaseUtils.getAssetsDir().getAbsolutePath() + File.separator + BaseUtils.getClientName()));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String bin = BaseUtils.getMcDir().toString() + File.separator;
		cl = new eURLClassLoader(GuardUtils.url.toArray(new URL[GuardUtils.url.size()]));
		boolean old = false;
		try {
			cl.loadClass("net.minecraft.client.Minecraft");
			old = true;
		} catch (Exception e) {
		}
		String user = answer.split("<br>")[1].split("<:>")[0];
		String session = EncodingUtils.xorencode(EncodingUtils.inttostr(answer.split("<br>")[1].split("<:>")[1]),
				Settings.protectionKey);

		Thread ch = new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					GuardUtils.check();
					try {
						Thread.sleep(30000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		ch.start();

		if (old) {
			Thread check = new Thread(new Runnable() {
				@Override
				public void run() {
					int z = 0;
					while (z < Settings.useModCheckerint) {
						GuardUtils.checkMods(answer, false);
						try {
							Thread.sleep(30000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						z++;
					}
				}
			});
			check.start();

			try {
				addWindowListener(new WindowListener() {
					public void windowOpened(WindowEvent e) {
					}

					public void windowIconified(WindowEvent e) {
					}

					public void windowDeiconified(WindowEvent e) {
					}

					public void windowDeactivated(WindowEvent e) {
					}

					public void windowClosed(WindowEvent e) {
					}

					public void windowActivated(WindowEvent e) {
					}

					public void windowClosing(WindowEvent e) {
						mcapplet.stop();
						mcapplet.destroy();
						System.exit(0);
					}
				});
				setForeground(Color.BLACK);
				setBackground(Color.BLACK);

				mcapplet = new Launcher(bin, GuardUtils.url.toArray(new URL[GuardUtils.url.size()]));
				mcapplet.customParameters.put("username", user);
				mcapplet.customParameters.put("sessionid", session);
				mcapplet.customParameters.put("stand-alone", "true");
				if (Settings.useAutoenter) {
					mcapplet.customParameters.put("server",
							Settings.servers[Frame.main.servers.getSelectedIndex()].split(", ")[1]);
					mcapplet.customParameters.put("port",
							Settings.servers[Frame.main.servers.getSelectedIndex()].split(", ")[2]);
				}
				setTitle(Settings.titleInGame);
				if (Frame.main != null) {
					Frame.main.setVisible(false);
					setBounds(Frame.main.getBounds());
					setExtendedState(Frame.main.getExtendedState());
					setMinimumSize(Frame.main.getMinimumSize());
				}
				setSize(Settings.width, Settings.height + 28);
				setMinimumSize(new Dimension(Settings.width, Settings.height + 28));
				setLocationRelativeTo(null);
				mcapplet.setForeground(Color.BLACK);
				mcapplet.setBackground(Color.BLACK);
				setLayout(new BorderLayout());
				add(mcapplet, BorderLayout.CENTER);
				validate();
				if (BaseUtils.getPropertyBoolean("fullscreen"))
					setExtendedState(JFrame.MAXIMIZED_BOTH);
				setIconImage(BaseUtils.getLocalImage("favicon"));
				setVisible(true);

				if (Settings.useConsoleHider) {
					System.setErr(new PrintStream(new NulledStream()));
					System.setOut(new PrintStream(new NulledStream()));
				}
				mcapplet.init();
				mcapplet.start();
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {
			Thread check = new Thread(new Runnable() {
				@Override
				public void run() {
					int z = 0;
					while (z < Settings.useModCheckerint) {
						GuardUtils.checkMods(answer, false);
						try {
							Thread.sleep(30000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						z++;
					}
				}
			});
			check.start();
			try {
				System.out.println("Running Minecraft");
				String jarpath = BaseUtils.getMcDir().toString() + File.separator;
				String minpath = BaseUtils.getMcDir().toString();
				String assets = BaseUtils.getAssetsDir().toString() + File.separator;
				System.setProperty("fml.ignoreInvalidMinecraftCertificates", "true");
				System.setProperty("fml.ignorePatchDiscrepancies", "true");
				System.setProperty("org.lwjgl.librarypath", jarpath + "natives");
				System.setProperty("net.java.games.input.librarypath", jarpath + "natives");
				System.setProperty("java.library.path", jarpath + "natives");
				if (BaseUtils.getPropertyBoolean("fullscreen")) {
					params.add("--fullscreen");
					params.add("true");
				} else {
					params.add("--width");
					params.add(String.valueOf(Settings.width));
					params.add("--height");
					params.add(String.valueOf(Settings.height));
				}
				if (Settings.useAutoenter) {
					params.add("--server");
					params.add(Settings.servers[Frame.main.servers.getSelectedIndex()].split(", ")[1]);
					params.add("--port");
					params.add(Settings.servers[Frame.main.servers.getSelectedIndex()].split(", ")[2]);
				}
				try {
					cl.loadClass("com.mojang.authlib.Agent");
					params.add("--accessToken");
					params.add(session);
					params.add("--uuid");
					params.add(EncodingUtils.xorencode(EncodingUtils.inttostr(answer.split("<br>")[0].split("<:>")[1]),
							Settings.protectionKey));
					params.add("--userProperties");
					params.add("{}");
					params.add("--assetIndex");
					params.add(Settings.servers[Frame.main.servers.getSelectedIndex()].split(", ")[3]);
				} catch (ClassNotFoundException e2) {
					params.add("--session");
					params.add(session);
				}
				params.add("--username");
				params.add(user);
				params.add("--version");
				params.add(Settings.servers[Frame.main.servers.getSelectedIndex()].split(", ")[3]);
				params.add("--gameDir");
				params.add(minpath);
				params.add("--assetsDir");
				if (Integer.parseInt(Settings.servers[Frame.main.servers.getSelectedIndex()].split(", ")[3].replace(".",
						"")) < 173) {
					params.add(assets + "assets/virtual/legacy");
				} else {
					params.add(assets + "assets");
				}
				boolean tweakClass = false;
				try {
					cl.loadClass("com.mumfrey.liteloader.launch.LiteLoaderTweaker");
					params.add("--tweakClass");
					params.add("com.mumfrey.liteloader.launch.LiteLoaderTweaker");
					tweakClass = true;
				} catch (ClassNotFoundException e) {
				}
				try {
					cl.loadClass("cpw.mods.fml.common.launcher.FMLTweaker");
					params.add("--tweakClass");
					params.add("cpw.mods.fml.common.launcher.FMLTweaker");
					tweakClass = true;
				} catch (ClassNotFoundException e) {
				}
				try {
					cl.loadClass("net.minecraftforge.fml.common.launcher.FMLTweaker");
					params.add("--tweakClass");
					params.add("net.minecraftforge.fml.common.launcher.FMLTweaker");
					tweakClass = true;
				} catch (ClassNotFoundException e) {
				}
				if (tweakClass) {
					Cl = "net.minecraft.launchwrapper.Launch";
				} else {
					Cl = "net.minecraft.client.main.Main";
				}

				Frame.main.setVisible(false);
				GuardUtils.delete(new File(assets + "assets/skins"));
				start.start();
			} catch (Exception e) {
			}
		}
	}

	public static Thread start = new Thread(new Runnable() {
		@Override
		public void run() {

			try {
				Class<?> start = cl.loadClass(Cl);
				Method main = start.getMethod("main", new Class[] { String[].class });
				main.invoke(null, new Object[] { params.toArray(new String[0]) });
			} catch (Exception e) {
				JOptionPane.showMessageDialog(Frame.main, e, "Ошибка запуска", javax.swing.JOptionPane.ERROR_MESSAGE,
						null);
				System.exit(0);
			}

		}
	});

	public void Splash(File file, File options) throws IOException {
		if (!file.exists()) {
			file.mkdir();
		}
		file = new File(file, "splash.properties");
		if (!file.exists()) {
			file.createNewFile();
			PrintWriter pw = new PrintWriter(file);
			try {
				pw.print("enabled=false");
			} finally {
				pw.close();
			}

		}
		File option = new File(options, "options.txt");
		if (!option.exists()) {
			option.createNewFile();
			PrintWriter pw = new PrintWriter(option);
			try {
				pw.print("lang:ru_RU");
			} finally {
				pw.close();
			}

		}
		File texture = new File(options, "resourcepacks");
		File[] packs = texture.listFiles();
		if (packs.length != 0) {
			Scanner sc = new Scanner(option);
			List<String> op = new ArrayList<String>();
			Boolean iii = false;
			while (sc.hasNextLine()) {
				String temp = sc.nextLine();
				if (temp.startsWith("resourcePacks")) {
					iii = true;
					temp = "resourcePacks:[";
					temp += "\"" + packs[0].getName() + "\"";
					temp += "]";
				} else if (temp.startsWith("skin:") && (BaseUtils.getClientName().endsWith("1.5.2")
						|| BaseUtils.getClientName().endsWith("1.6.4"))) {
					iii = true;
					temp = "skin:" + packs[0].getName();
				}
				op.add(temp);

			}
			if (!iii) {
				if (BaseUtils.getClientName().endsWith("1.5.2") || BaseUtils.getClientName().endsWith("1.6.4")) {
					String temp = "skin:" + packs[0].getName();
					op.add(temp);
				} else {
					String temp = "resourcePacks:[";
					temp += "\"" + packs[0].getName() + "\"";
					temp += "]";
					System.out.println(temp+"__________________");
					op.add(temp);
				}

			}
			PrintWriter pw = new PrintWriter(option);
			try {
				for (int i = 0; i < op.size(); ++i)
					pw.println(op.get(i));
			} finally {
				pw.close();
			}
			
		}


		System.out.println("HAHAHAHA______");
		try {
			System.out.println("HAHAHAHA");
			File from = new File(options,"server"+ File.separator+"servers.dat");
			File to = new File(options, "servers.dat") ;
			Files.copy(from.toPath(), to.toPath());
		} catch (Exception e ) {
			System.out.println("HAHAHAHA");
		}
	}
}
