package SimulasiBiayaHaji;

import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);

    // Paket Haji dan Tambahan Opsional
    double paketHajiReguler = 40000000;
    double paketHajiPlus = 70000000;
    double paketHajiVIP = 120000000;
    double opsionalAsuransi = 5000000;
    double opsionalHotel = 10000000;
    double opsionalKatering = 7000000;
    double inflasi = 0.05;
    double subsidiUsiaLanjut = 0.10;
    double diskonUangMuka = 2000000;

    // Input Data
    System.out.print(
        "Pilih paket haji:\n1. Reguler (Rp40.000.000)\n2. Plus (Rp70.000.000)\n3. VIP (Rp120.000.000)\nMasukan pilihan Anda (1-3): ");
    double pilihanPaketHaji = 0;
    int pilihanPaket = input.nextInt();
    if (pilihanPaket == 1) {
      pilihanPaketHaji = paketHajiReguler;
    } else if (pilihanPaket == 2) {
      pilihanPaketHaji = paketHajiPlus;
    } else if (pilihanPaket == 3) {
      pilihanPaketHaji = paketHajiVIP;
    }

    System.out.print(
        "Tambahan opsional yang tersedia:\n1. Asuransi Kesehatan (Rp5.000.000)\n2. Hotel Masjidil Haram (Rp10.000.000)\n3. Upgrade layanan katering (Rp7.000.000)\nMasukan angka tambahan yang Anda pilih (pisahkan dengan koma, contoh: 1,3): ");
    String[] pilihanTambahan = input.next().split(",");

    System.out.print("Masukan usia calon jamaah: ");
    int usia = input.nextInt();

    System.out.print("Masukan uang muka yang telah dibayarkan (Rp): ");
    int uangMuka = input.nextInt();

    System.out.print("Masukan jumlah tahun hingga keberangkatan: ");
    int tahun = input.nextInt();

    System.out.print("Masukan simpanan awal (Rp): ");
    int simpananAwal = input.nextInt();

    System.out.print("Masukan tabungan per bulan (Rp): ");
    int tabunganPerBulan = input.nextInt();

    // Hitung Biaya Tambahan
    double totalTambahan = 0;
    for (String tambahan : pilihanTambahan) {
      int pilihan = Integer.parseInt(tambahan);
      if (pilihan == 1) {
        totalTambahan += opsionalAsuransi;
      } else if (pilihan == 2) {
        totalTambahan += opsionalHotel;
      } else if (pilihan == 3) {
        totalTambahan += opsionalKatering;
      }
    }

    // Diskon/Subsidi
    double totalDiskon = 0;
    if (usia >= 60) {
      totalDiskon += subsidiUsiaLanjut;
    }
    if (uangMuka >= 10000000) {
      totalDiskon += diskonUangMuka;
    }

    // Hitung Biaya Setelah Inflasi
    for (int i = 1; i <= tahun; i++) {
      pilihanPaketHaji *= (1 + inflasi);
      totalTambahan *= (1 + inflasi);
    }
    double totalBiayaSetelahInflasi = pilihanPaketHaji + totalTambahan;

    // Hitung Biaya Akhir
    double biayaAkhir = totalBiayaSetelahInflasi - (totalBiayaSetelahInflasi * totalDiskon);

    // Output Simulasi Biaya Haji
    System.out.printf(
        "\n### Simulasi Biaya Haji ###\nPaket yang dipilih: Rp%.0f\nBiaya tambahan: Rp%.0f\nTotal biaya awal: Rp%.0f\nTotal biaya setelah inflasi: Rp%.0f\nDiskon/Subsidi: Rp%.0f\nBiaya akhir setelah diskon/subsidi: Rp%.0f\n",
        pilihanPaketHaji, totalTambahan, pilihanPaketHaji + totalTambahan, totalBiayaSetelahInflasi,
        totalBiayaSetelahInflasi * totalDiskon, biayaAkhir);

    // Simulasi Menabung
    int bulanMenabung = (int) Math.ceil((biayaAkhir - uangMuka - simpananAwal) / tabunganPerBulan);
    int tahunMenabung = bulanMenabung / 12;
    int bulanSisa = bulanMenabung % 12;

    // Output Simulasi Menabung
    System.out.printf(
        "\n### Simulasi Menabung ###\nSimpanan awal: Rp%d\nTabungan bulanan: Rp%d\nUang muka: Rp%d\nWaktu menabung: %d bulan (%d tahun %d bulan)\n",
        simpananAwal, tabunganPerBulan, uangMuka, bulanMenabung, tahunMenabung, bulanSisa);

    input.close();
  }
}
