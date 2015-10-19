package net.launcher.run;


public class Settings
{
	/** Настройка заголовка лаунчера */
	public static final String  title		         = ""; //Заголовок лаунчера
	public static final String  titleInGame  	     = ""; //Заголовок лаунчера после авторизации
	public static final String  baseconf		     = ""; //Папка с файлом конфигурации
	public static final String  pathconst		     = "" + "/%SERVERNAME%"; //Конструктор пути к папке с MC
	public static final String  skins                = "MinecraftSkins/"; //Папка скинов
	public static final String  cloaks               = "MinecraftCloaks/"; //Папка плащей
	/** Параметры подключения */
	public static final String  domain	 	         = "";//Домен сайта
	public static final String protectionKey	 = ""; //Ключ защиты сессии. Никому его не говорите.
	public static final String key1              = ""; //16 Character Key Ключ пост запросов
	public static final String key2              = ""; //16 Character Key Ключ пост запросов
	public static final String  siteDir		         = "launcher";//Папка с файлами лаунчера на сайте
	public static final String  updateFile		     = domain + "/launcher/launcher/launcher";//Ссылка на обновления лаунчера. Не писать на конце ".exe .jar"!
	public static final String  buyVauncherLink      = "http://plati.ru/"; //Ссылка на страницу покупки ваучеров
	public static final String  iMusicname           = "";//001.mp3";
	public static final String[] p = {"wireshark", "cheat"};  //Список запрещенных процессов.
	public static final String http = "http://";   //Протокол подключения https:// если есть ssl сертификат
	
	public static int height                         = 532;      //Высота окна клиента
	public static int width                          = 900;      //Ширина окна клиента
        
	public static String[] servers =
	{
            "Offline, localhost, 25565, 1.5.2"
	};

	/** Настройка панели ссылок **/
	public static final String[] links = 
	{
		//Для отключения добавьте в адрес ссылки #
		//" Наш сайт ::http://",
	};
    public static final String registrationUrl = "http://";

	/** Настройки структуры лаунчера */
	public static boolean useAutoenter	         =  true;  //Использовать функцию автозахода на выбранный сервер
	public static boolean useRegister		     =  true;   //Использовать Регистрацию в лаунчере
	public static boolean useMulticlient		 =  true;   //Использовать функцию "по клиенту на сервер"
	public static boolean useStandartWB		     =  true;   //Использовать стандартный браузер для открытия ссылок
	public static boolean usePersonal		     =  true;   //Использовать Личный кабинет
	public static boolean customframe 		     =  true;   //Использовать кастомный фрейм
	public static boolean useConsoleHider		 =  false;  //Использовать скрытие консоли клиента
	public static boolean useModCheckerTimer	 =  false;   //Перепроверка jar через 30 секунд
	public static int     useModCheckerint       =  false ? 2 : 0;      //Количество раз перепроверки jar во время игры
	public static boolean assetsfolder           =  false;  //Скачивать assets из папки, или из архива (true=из папки false=из архива) в connect.php должно быть так же.
	

	public static boolean debug		 	         =  true; //Отображать все действия лаунчера (отладка)(true/false)
	public static boolean drawTracers		     =  false; //Отрисовывать границы элементов лаунчера
	public static final String masterVersion     = "final_RC4"; //Версия лаунчера

	public static boolean patchDir 		         =  true; //Использовать автоматическую замену директории игры (true/false)
	
	public static void onStart() {}
	public static void onStartMinecraft() {}
}
