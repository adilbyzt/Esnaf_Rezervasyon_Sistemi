# esnaf-rezervasyon-sistemi

# 1. Giriş

## 1.1. Projenin Altyapısı

Proje Android Studio kullanılarak Java kodlama dili ile geliştirilmiştir. Projede
Veritabanı olarak Firebase FireStore kullanılmıştır.

## 1.2. Projenin Amacı

Geliştirmiş olduğumuz proje olan Esnaf Randevu Sistemi, müşteri ile esnaf arasındaki
bağı güçlendirmek hedefiyle oluşturulmuştur. Müşteriler fazla çaba göstermeden senkron
şekilde esnaflardan randevu oluşturabilirler.

## 1.3. Projenin Kullanım Alanları

Esnaf Randevu Sistemimizi, randevu ile çalışan bütün işletmeler, esnaflar, veya
hastaneler kullanabilirler. Sistemin kullanılabileceği ortamlara örnek verecek olursak;

Hastaneler, Berberler, Güzellik Salonları, Araç Tamir ve Bakım (Sanayi) noktaları, Dişçiler,
Restoranlar vb. gibi birçok sektördeki esnaflar uygulamayı kullanabilir.


# 2. Projenin Genel Tanıtımı

Esnaflar sisteme gerekli bilgilerini girerek kayıt olurlar. Kayıt olduktan sonra mail ve şifre
bilgileri Authentication kısmına diğer bilgileri ise Firebase Firestore veritabanına kendilerine
özel Id ‘leri ile kayıt edilir. Kayıt işlemi tamamlandıktan sonra esnaf giriş yap sayfasına
giderek mail ve şifresiyle giriş yapabilir. Sisteme giriş yaptıktan sonra kendi dükkanına ait
olan randevuların tamamını görüntüleyebilir. Randevunun üzerine tıklayarak randevuyu
oluşturan kişinin Ad Soyad ve Telefon Numarası bilgilerini görüntüleyebilir. Oluşturulmuş
olan randevuyu iptal edebilir.

Müşteriler açılış sayfasında bulunan Randevu Oluştur butonuna basıp randevusunu
oluşturmaya başlayabilir. Butona bastıktan sonra karşısına sisteme üye olan esnafların listesi,
esnafların Adı, Çalışma Saatleri ve Adresleriyle bilgileriyle görüntülenir. Ayrıca müşteri,
sayfanın üst kısmında bulunan arama kutucuğuna basarak aramak istediği esnafın adına göre
listeyi filtreleyebilir. İstenilen esnafın üzerine tıklandıktan sonra rezervasyonun tarihinin ve
rezervasyonun saatinin seçileceği sayfa açılır. Bu sayfadaki rezervasyon saatlerinin
başlangıcı, bitişi ve randevunun sıklığı her esnaftan üye olurken alınan esnafın çalışma
saatleri ve kaç dakikada bir yeni randevu almak istediğine göre saatler oluşturulur ve ortaya
çıkan sayıda tıklanabilir CardViewlar, Adapter kullanılarak oluşturulur. Liste sayfanın üst
kısmında bulunan tarihe göre veritabanından o tarihteki randevular sorgulanıp dolu randevu
saatlerine DOLU yazılarak kırmızı renkte gösterilir ve DOLU randevular üzerine tıklanamaz.
Dolu olmayan saatler yeşil renkte BOŞ olarak gösterilir ve müşteri bu saatlerden istediği saat
aralığına tıklar ve sonraki sayfaya geçiş yapar. Sonraki sayfada müşteriden Ad Soyad ve
Telefon numarası bilgilerini girmesi istenilir. Bu bilgiler boş geçilemez ve belli kısıtlamalara
sahiptir. Ad Soyad olarak sadece harf girilebilir ve boşluk bırakılabilir. Telefon numarası
olarak sadece rakam girilebilir ve 10 rakam girilmesi zorunludur. Bilgiler girildikten sonra
Randevuyu Tamamla butonuna basıldığında girilen bilgilerde herhangi bir hata varsa sistem
hatayı ekranda gösterir. Bilgilerde herhangi bir hata yoksa sistem sonraki sayfaya geçiş yapar
ve ekrana Randevu Başarıyla Oluşturuldu şeklinde bir yazı bulunur. Aynı zamanda
oluşturulmuş randevunun Tarih ve Saati, Oluşturan kişinin Adı ve Soyadı ile birlikte ekranda
gösterilir.


## 2.1. Projenin Ekran Görüntüleriyle Detaylı Tanıtımı

2.1.1. **Müşteri Randevu Oluşturma Sayfaları**

```
2.1.1.1 MainActivity (Açılış) Sayfası
```

Uygulama ilk açıldığında ekrana Müşterilerin

randevu oluşturabileceği bir buton, esnafların giriş

yapabileceği bir buton ve esnafların üye olabileceği

bir buton gelir.
<br/><br/>
```
2.1.1.2 EsnafSecim Sayfası
```
Randevu oluştur butonuna basıldığında müşteriler,

Sistemde kayıtlı olan esnafların adını, çalışma saatlerini

ve adreslerini görüntüleyebilir. Sayfanın üstünde bulunan

Arama çubuğuna basıldığında, Randevu alınmak istenilen

esnafın adı girilerek diğer esnaflar arasından randevu

almak istediği esnafı arayabilir.

<br/><br/>
```
2.1.1.3 EsnafSecim Sayfası (filtreli)
```

Arama çubuğundan istenilen esnaf aranabilir ve bilgileri

görüntülenebilir.

Seçilen esnafa özel, esnafın çalışma saatlerine göre

müşterinin randevusunun tarih ve saatini seçebileceği

bir sayfa açılır. Sayfanın üst kısmından randevu tarihi

seçilir ve seçilelen randevu tarihine göre alt kısımdaki

dolu saatler veritabanından çekilerek güncellenir.

Müşterinin dolu olan butonlara basması kısıtlanmıştır.

Sadece boş olan butonlara basılabilir. Butona basıldığında

Randevuyu oluşturan müşterinin bilgilerinin alınacağı bir

Sayfa açılır.
<br/><br/>
```
2.1.1.4 TarihSaatSecim Sayfası
```
Seçilen esnafa özel, esnafın çalışma saatlerine göre
müşterinin randevusunun tarih ve saatini seçebileceği
bir sayfa açılır. Sayfanın üst kısmından randevu tarihi
seçilir ve seçilelen randevu tarihine göre alt kısımdaki
dolu saatler veritabanından çekilerek güncellenir.
Müşterinin dolu olan butonlara basması kısıtlanmıştır.
Sadece boş olan butonlara basılabilir. Butona basıldığında
Randevuyu oluşturan müşterinin bilgilerinin alınacağı bir
Sayfa açılır.
<br/><br/>
```
2.1.1.5 RandevuOlusturanınBilgileri Sayfası
```
Randevuyu oluşturan müşterinin Ad Soyad ve Telefon
Numarası bilgileri alınır. EditTextler boş bırakılıp geçilemez.
Ad Soyad kısmında sadece harf girilebilir ve boşluk
bırakılabilir. Telefon Numarası EditTextine sadece rakamlar
girilebilir ve 10 Rakam girilmesi istenir. 10 rakam
girilmediğinde program Sonraki sayfaya geçmez ve hata
verir. Butona basıldığında girilen bilgilerde herhangi bir
sorun yok ise veriler, seçilmiş olan esnafın
randevularına seçilmiş bilgilerle beraber kayıt edilir ve
RandevuOluşturuldu sayfasına geçiş yaparak oluşturulan
randevunun bilgileri görüntülenir.
<br/><br/>
```
2.1.1.6 RandevuOlusturuldu Sayfası
```
Randevunun oluşturulmuş olduğunun bilgisi ekrana
yazdırılır. Randevuyu oluşturan kişinin Adı Soyadı, Randevu
Tarihi ve Randevu Saati ekranda gösterilir. Anasayfa
butonuna basıldığında Açılış Sayfasına gidilir.

2.1.2. **Esnaf Kayıt Sayfaları**
```
2.1.2.1. EsnafKayit Sayfası
```
Açılış sayfasından Esnaf Kayıt Ol butonuna basılır ve
Ekrana esnafın kayıt olması için gerekli bilgilerin Alınacağı
sayfa gelir. Bu sayfada sistem için gerekli olan bilgilerden
Esnaf İsmi, Esnaf Email Adresi, Esnaf Şifresi, Esnaf Adresi,
Esnaf Telefon Numarası, Esnafın Açılış Saati, Esnafın
Kapanış Saati, Esnafın Randevu Aralığı gibi bilgiler esnaftan
alınır. Bu bilgilerden Email ve Şifre Firebase Auth sistemi ile
kayıt oluşturulup bu kayıt üzerinden giriş yapılması için
alınır. Diğer veriler FireBase FireStore a kaydedilerek sistem
gereksinimleri için kullanılır. Esnafın Açılış, Kapanış ve
Randevu Aralığı her esnaf için değişiklik gösterebileceği için
kayıt aşamasında esnafa özel çalışma saatleri seçmesi
istenilir ve esnafın randevu Aralıkları buna göre oluşturulur.
<br/><br/>

```
2.1.1.2. EsnafKayit Kontrolleri
```
Girilecek olan verilerin her birisi kontrol edilir ve eğer veri
istenilen gibiyse veritabanına kaydedilir. Veri eğer istenilen
formatta değilse hata mesajı verilerek esnaf uyarılır. Hiçbir veri boş bırakılamaz eğer veri boş bırakılırsa “Bu Alan Boş Bırakılamaz” şeklinde hata mesajı ekrana yazdırılır.
Esnaf İsim için girilen veri sadece harf ve rakam içerebilir ve
girilebilecek maksimum veri uzunluğu 30 karakterdir.
Esnaf Email için girilen veriler email formatına uygun
olmalıdır ve Türkçe karakterler içermemelidir. Girilebilecek
maksimum veri uzunluğu 50 karakterdir.
Esnaf Şifre için girilen veriler minimum 6 karakter olmalıdır.
Girilebilen maksimum karakter sayısı 30 karakterdir.
Esnaf Adres için maksimum karakter sayısı 200 karakterdir.
Esnaf Telefon için sadece rakam girilebilir. 10 rakam
altındaki herhangi bir sayı kabul edilmez ve hata verir.
Esnaf Açılış, Kapanış değerleri 24 saatin hepsini
İçeren spinner’da bulunur. Aralık değerlerini geliştirici belirtmiştir.Kullanıcı verilerini hatasız girdikten sonra kayıt başarılı
Mesajı görüntülenerek giriş sayfasına aktarılır.

2.1.3. **Esnaf Giriş Sayfaları**
```
2.1.3.1. EsnafGiris Sayfası
```
Açılış Sayfasında Esnaf Giriş Yap butonuna tıklanarak
Esnafın giriş yapabileceği sayfa açılır. Esnaf kayıt olurken
Girmiş olduğu Email ve Şifre bilgilerini girerek sisteme
Giriş yapabilir. Her esnafın kayıtlı Email ve Şifreleri
Firebase Auth sisteminde tutulur ve sisteme giriş yaparken
Bu sistem üzerinden kontrol edilerek esnaf kendi sistemine
Giriş yaptırılır. Eğer girilen Email veya Şifre yanlış ise
Sistem Hata mesajı verir.
<br/><br/>

```
2.1.3.2. EsnafRandevuGoruntule Sayfası
```
Esnaf verilerini doğru girdikten sonra kendisine özel olan,
Kayıt olurken seçmiş olduğu açılış saatine, kapanış saatine
Ve randevu aralığına göre oluşturulmuş randevu listesini
Görüntüler. Sayfanın üst kısmında bulunan takvimden
İstediği tarihteki randevuları listeleyebilir ve hangi
saatlerdeki randevuların dolu olduğunu görüntüleyebilir.
Aynı zamanda yeşil yenileme butonuna basarak listenin
En güncel halini görüntüleyebilir. Esnafın boş olan randevu
Saatlerinin üzerine tıklaması kısıtlanmıştır. Sadece dolu olan
Saatler üzerine tıklanılabilir. Dolu olan saatler üzerine
tıklanıldığında randevuyu oluşturan kişinin bilgilerinin
bulunduğu ve randevuyu iptal edebileceği bir buton
bulunur.
<br/><br/>
```
2.1.3.3. RandevuIptal Sayfası
```
Dolu olan saatin üzerine tıklandığında RandevuIptal
Sayfasına aktarılır. Tıklanan zaman aralığı tarihi ile birlikte
Ekranın en üst kısmında gösterilir. Ekranın orta kısmında,
O randevuyu oluşturan kişinin Ad Soyad ve Telefon
Numarası bilgileri bulunur. Esnaf ekranın alt kısmında
bulunan Randevuyu İptal Et butonuna basarak
oluşturulmuş olan randevuyu iptal ederek veritabanından
silinmesini sağlayabilir.

2.1.4. **Veritabanının Anlatılması ve Görselleri**

Veritabanında esnaflar adındaki Collection altında, üye olan her esnaf benzersiz bir ID ile

birlikte document olarak kayıt edilmiştir.

Her esnafın ID sinin altında bulunan fieldlarda, Esnafın üye olurken girmiş olduğu bütün
bilgiler tutulur.

```
2.1.4.1. esnaflar Collection’ı ve Her Esnafın Benzersiz ID ‘ si.
```
```
2.1.4.2. Her esnafın ID si altında bulunan field’ lar.
```

```
Müşterinin oluşturmuş olduğu randevunun tarihi veritabanında esnafın ID sinin altına, randevunun
Tarihiyle birlikte Collection olarak kaydedilir. Tarih collection’ı altına o tarihteki seçilmiş olan saat
aralığı (slotunun id) si kaydedilir. Aşağıdaki resimdeki 1,3,4,8,9 isimleriyle görülen documentlar
aslında oluşturulmuş olan randevuların id sidir. Bu idlerin her birinin altında randevuyu oluşturmuş
kişinin bilgileri ve randevu bilgileri yer alır.
```
**2.1.4.3. Oluşturulan Randevunun Bilgileri Ve Randevuyu Oluşturanın Bilgilerin** in **Tutulduğu Yerler.**


# 3. SONUÇ

Her geçen gün gelişmekten olan dev markalar, AVM ler ve zincir firmalar yüzünden
esnafların büyümesi durma noktasına gelmiştir.

Bu proje esnaf veya işletmeleri büyük bir iş yükünden kurtararak mobil ortamda kolayca
randevu alınıp, randevuların hem esnaf hem de müşteri için görüntülenmesini sağlamıştır.
Uygulamanın arayüzü oldukça basit tutulmuştur, bu sayede telefon kullanmasını fazla
bilmeyen bir insan bile uygulamayı rahatlıkla kullanabilecektir.

Kodlar olabildiğince sade yazıldığından sayfalar arasındaki bekleme süresi ve verilerin
veritabanı ile olan işlemleri çok kısa sürmektedir. Bu sayede uygulama oldukça hızlı ve
akıcıdır.

Uygulamanın Veritabanının internet ortamında bulunması her ortamdan erişilebilir ve
erişiminin çok kolay olması dolayısıyla projeye hem hız hem de verimlilik sağlamıştır.

Veritabanında Firebase FireStore kullanmamızın sebebi collection ve documentlar ile
daha sade ve anlaşılır bir veritabanı oluşturmaktır. Veriler uygulama ile senkron şekilde
veritabanına kayıt edilir, silinir veya güncellenir. Bu sayede esnaf ve müşteri herhangi bir hata
almadan sistemi sorunsuz bir şekilde kullanabilmektedir.

Bu projeyi yaparak hem kendimizi geliştirip hem de kötü durumda olan esnaflar için
faydalı bir proje ortaya çıkarttık.

Bu projeyi yaparken bilmediğimiz birçok hata alıp, bu hataları araştırarak veya kendi
yöntemlerimizle çözüme ulaştırarak hata çözme konusunda kendimizi oldukça geliştirmiş
olduk. Android Programlama konusunda birçok yeni bilgi ve yöntem öğrenip proje üzerinde
bu yöntemleri uyguladık.

Bu projenin birçok esnaf ve müşterisi tarafından kullanılabileceğini ve kendilerine katkı
sağlayabileceğini düşünüyoruz.


