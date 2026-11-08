SharedPreferences pada Aplikasi Mobile – E-Wallet "DompetKu"
Tugas P10 Pemrograman Mobile · Prodi STI · Universitas Muhammadiyah Kotabumi

Nama  : M. Vasco Al Ghazi
NIM   : 2459201109
Kelas : 5A
Dosen : Ryan Aji Wijaya, S.Kom., M.M.S.I

Keterangan:
1 Pengguna e-wallet sering membuka aplikasi di tempat umum, jadi butuh saldo yang bisa disembunyikan, serta nama sapaan dan mode gelap yang diingat. DompetKu menyimpan preferensi ini supaya tidak perlu diatur ulang setiap aplikasi dibuka.
2 Yang disimpan di SharedPreferences: nama panggilan (String), mode gelap (Boolean), dan sembunyikan saldo (Boolean). Alasannya: datanya kecil, berbentuk key-value sederhana, dan dibaca setiap aplikasi dibuka.
3 Data transaksi (jenis, nominal, waktu) disimpan di SQLite karena terus bertambah, berstruktur, dan perlu di-query serta dijumlahkan; saldo dihitung dari total transaksi. Nominal yang sedang diketik di form tidak disimpan karena hanya data sementara. Data sensitif seperti PIN/password tidak boleh disimpan di SharedPreferences karena filenya tidak dienkripsi.
4 Cara kerja: kelas PrefsManager membungkus SharedPreferences. Data ditulis dengan edit().putXxx().apply() saat tombol Simpan ditekan, lalu dibaca ulang di onResume() MainActivity sehingga sapaan dan tampilan saldo langsung berubah.
5 Bukti: setelah nama diubah, saldo disembunyikan, dan mode gelap diaktifkan, lalu aplikasi di-force stop dan dibuka lagi, semua pengaturan tetap sesuai pilihan terakhir, dan riwayat transaksi juga masih ada.

Kode: https://github.com/Vascoal/E-Wallet-app

Menurut kalian, data apa lagi di aplikasi e-wallet yang cocok disimpan di SharedPreferences, dan mana yang sebaiknya masuk database?

#android #java #pemrogramanmobile #sharedpreferences #umko
