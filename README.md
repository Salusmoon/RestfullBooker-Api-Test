# 🧪 Restful Booker API Test Projesi

Bu proje, [Restful Booker](https://restful-booker.herokuapp.com) adlı bir test sisteminin API’lerini kontrol etmek için hazırlanmıştır. Amaç, sistemin doğru çalışıp çalışmadığını otomatik testlerle kontrol etmektir.

Not: Testler birbirne bağlı ve sırayla çalışmaktadır. Bu sebeple ayrı çalıştırmalarda hata alınabilir.

---

## 🔧 Neler Yapıyor?

- Sisteme giriş (token alma)
- Rezervasyon oluşturma
- Rezervasyonu güncelleme (tam veya kısmi)
- Rezervasyonu silme
- Yapılan işlemleri kontrol etme


---

## 📁 Proje Yapısı

- `tests`: Test dosyaları burada bulunur.
- `request`: Gönderilen veriler için sınıflar burada yer alır.
- `response`: Alınan veriler için sınıflar burada yer alır.
- `utils`: Ortak kullanılan veriler ve yardımcı dosyalar.

---

## ▶️ Nasıl Çalıştırılır?

1. Projeyi bilgisayarına indir:

   ```bash
   git clone https://github.com/Salusmoon/RestfullBooker-Api-Test.git
