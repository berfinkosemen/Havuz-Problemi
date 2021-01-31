# Havuz-Problemi


 Uygulama çalıştırıldığında, kullanıcıdan graf oluşturulabilmesi için gerekli olan graf bilgileri istenir. Önce düğüm sayısı, düğümlerin adları ardından başlangıç ve bitiş düğümünün belirlenmesi ve son olarak düğümler arasındaki bağlantıların kullanıcıdan alınması gerçekleştirilir. Bilgi alımının tamamlanmasının ardından tamamla butonuna basılarak, alınan bilgilerin eşliğinde grafın maximum flow ‘u ve minimum cut ‘ı bulunur. Görselleştir butonuna basılması ile graf görselleştirilir ve yine buton yardımı ile maximum flow adım adım görselleştirilir.  
II.	TEMEL BILGILER
Program Java dilinde geliştirilmiş olup, Intelij idea tümleşik geliştirme ortamı  (IDE) kullanılmıştır. Görselleştirme için Graph Stream kütüphanesi kullanılmıştır.

III.	TASARIM 
Oyunu Havuz Problemi projesinin programlanma aşamaları aşağıdaki alt başlıklarda ayrıntılı bir şekilde açıklanmıştır.

A.	Algoritma
Program başlatıldığında kullanıcıdan musluk sayısı alınır. Butona basılması ile yeni bir pencere açılır ve kullanıcının musluk isimlerini girmesi istenir. Ardından ana sayfa da beliren başlangıç ve sonuç musluklarının belirtimi gerçekleştirildikten sonra beliren buton ile kenar bağlantılarının belirlendiği sayfa açılır. Sırasıyla her düğümün bağlantıları teker teker alınır ve onaylanır. Bu işlemin tamamlanmasının ardından tamamla butonuna basarak kullanıcıdan alınan verilerin maksimum akış algoritmasına iletimi gerçekleştirilir. Önce yol bulma ardından kenarların değerlerinin hesaba katılıp maksimim akışın bulunması sağlanır. Ardından minimum cut algoritması çağırılarak kesilmesi gereken boruların tespiti yapılır. Bulunan değerler başka bir pencere açılarak görselleştirilir. Görselleştir butonu kullanılarak graf  kullanıcıya yansıtılır ve buton yardımı ile adım adım maksimum akış görselleştirilir. 
B.	Kullanılan Fonksiyonlar
Main.java.class
•	public static void main(String[] args) throws ClassNotFoundException UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException
Kullanıcı ara yüzü olan Gui1 class ını çağırır.

MinCut.java.class
•	static string mincut(int[][] graph, int s, int t)
Bfs algoritması yardımı ile bulunan maksimum akış değerinin ardından dfs algoritması ile minimum cut değerinin bulup string halinde döndürür.
•	public void onBackPressed()private static boolean bfs(int[][] rGraph, int s,int t,  int[] parent) 
Bfs  algoritması yardımı ile başlangıç ve bitiş düğümü arasındaki olası yolları bulur.
•	private static void dfs(int[][] rGraph, int s,boolean[] visited)
Başlangıç noktasından ulaşılabilecek büt5ün düğümleri bulur.Hangi level ve subleveller başarıyla tamamlandı ise onlara tik işareti ekler. 

MaxFlow.java class
•	private static boolean bfs(int[][] rGraph, int s,int t,  int[] parent)
•	Bfs  algoritması yardımı ile başlangıç ve bitiş düğümü arasındaki olası yolları bulur.
•	int fordFulkerson(int graph[][], int s, int t)
Bfs fonksiyonundan dönen değerlerin sonsuz döngüye alınması ve bu yollardan geçen akışların hesaplanıp, toplanmasını sağlar. Sonuç olarak maksimim akışın değerini döndürür.
•	List<Integer> queue_list(int[][] graph, int s, int t)
Bfs fonksiyonundan dönen değerlerin sonsuz döngüye alınması ve döndürülen yol değerlerini teker teker kaydedilmesini sağlar. Uğranması gereken düğümleri sırayla döndürür.
•	List<Integer>  flow_list(int graph[][], int s, int t)
Bfs fonksiyonundan dönen değerlerin sonsuz döngüye alınması ve döndürülen yol değerlerinden yararlanarak kenarlarda yapılması gereken akış değişimlerinin teker teker kaydeder.

Gui1.java class

•	public Gui1()
Arayüzün tasarımını içerir. Kullanıcıdan düğüm sayısı istenir. Yeni bir sayfa açar ve alınan değere göre düğüm isimlerinin girildiği kutucukları oluşturur. Onaylanmasının ardından ana sayfaya başlangıç ve bitiş düğümünün alınacağı satırları ekler. Onaylanmasından sonra yeni bir buton daha ekler ve bu buton yeni bir sayfa açar. Bu sayfada bağlantı yapılması istenen düğümün adı istenir. Girildikten sonra yeni bir sayfa açar ve açılan sayfada düğümün bağlantı sayısına göre satır ekler. Tamamla butonuna basılırsa elde edilen sonuçları yeni bir pencerede açar. Görselleştir butonuna basılırsa grafı görselleştirir ve adım adım maksimum akışım takip edilmesini sağlar.
•	public void writeNodeNames()
Düğüm isimlerini ara yüzde görüntüler.
•	public void baglantiMatrixiOlustur()
Activity yi çalıştırır. Oyun verilerini ekranda gösterir.
•	public void findMaxFLow()
Maksimin akış ve minimum cut algoritmalarını kullanabilmek için gerekli olan kenarlar arasındaki akış değerlerini gösteren grafı oluşturur. Maxflow algoritmasını çağırarak maksimim akışın bulunmasını sağlar. Ardından min cut algoritmasını çağırarak minimum cut için kesilmesi gereken kenarın düğümlerini string halinde alır. Bu elde edilen değerleri yeni bir pencere içinde görüntüler.
•	public int[][] find_graph()
Maksimin akış ve minimum cut algoritmalarını kullanabilmek için gerekli olan kenarlar arasındaki akış değerlerini gösteren grafın oluşturulmasını sağlar.
•	protected String styleSheet
Grafın görselleştirilmesi için gereken düğüm  ve kenar dizaynlarının kodunu içerir.

C.	Karşılaşılan problemler ve çözüm yaklaşımları
•	Graph Stream kütüphanesinin yüklenmesinde zorluk yaşandı. Kaynak bulumu ile sorun çözüldü.
•	Grafın görselleştirilmesinin ardından akış adımlarının dinamik olarak gösterilmesinde zorluk yaşandı. Buton eklenmesi ve adımların takip edilmesi sağlandığında hata çözüldü. 
•	

D.	Sonuçlar
Program istenilen kriterlerin hepsini sağlamaktadır. Arayüz üzerinden kullanıcıdan,  musluk sayısı (node/düğüm), musluk arasında bağlantı bilgisini veren boru hattı (edge/kenar) ve boru hatlarının kapasiteleri alınıyor. Alınan bilgilere göre bir graf yapısı dinamik olarak gösteriliyor. Maksimum akış adımları dinamik olarak graf üzerinde gösteriliyor.
