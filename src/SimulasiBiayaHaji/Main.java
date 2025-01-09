package SimulasiBiayaHaji;

import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);

    // Konstanta Paket Haji dan Tambahan Opsional
    final double[] PAKET_HAJI = { 40_000_000, 70_000_000, 120_000_000 };
    final double[] OPSIONAL = { 5_000_000, 10_000_000, 7_000_000 };
    final double INFLASI = 0.05, SUBSIDI_USIA_LANJUT = 0.10, DISKON_UANG_MUKA = 2_000_000;

    // Input Data
    System.out.print("Pilih paket haji:\n1. Reguler (Rp40.000.000)\n2. Plus (Rp70.000.000)\n3. VIP (Rp120.000.000)\nMasukan pilihan Anda (1-3): ");
    double pilihanPaketHaji = PAKET_HAJI[input.nextInt() - 1];

    System.out.print("Tambahan opsional yang tersedia:\n1. Asuransi Kesehatan (Rp5.000.000)\n2. Hotel Masjidil Haram (Rp10.000.000)\n3. Upgrade layanan katering (Rp7.000.000)\nMasukan angka tambahan yang Anda pilih (pisahkan dengan koma, contoh: 1,3): ");
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
      totalTambahan += OPSIONAL[Integer.parseInt(tambahan) - 1];
    }

    // Diskon/Subsidi
    double totalDiskon = (usia >= 60 ? SUBSIDI_USIA_LANJUT : 0) + (uangMuka >= 10_000_000 ? DISKON_UANG_MUKA : 0);

    // Hitung Biaya Setelah Inflasi
    for (int i = 1; i <= tahun; i++) {
      pilihanPaketHaji *= (1 + INFLASI);
      totalTambahan *= (1 + INFLASI);
    }
    double totalBiayaSetelahInflasi = pilihanPaketHaji + totalTambahan;

    // Hitung Biaya Akhir
    double biayaAkhir = totalBiayaSetelahInflasi - (totalBiayaSetelahInflasi * totalDiskon);

    // Output Simulasi Biaya Haji
    System.out.printf("\n### Simulasi Biaya Haji ###\nPaket yang dipilih: Rp%.0f\nBiaya tambahan: Rp%.0f\nTotal biaya awal: Rp%.0f\nTotal biaya setelah inflasi: Rp%.0f\nDiskon/Subsidi: Rp%.0f\nBiaya akhir setelah diskon/subsidi: Rp%.0f\n",
        pilihanPaketHaji, totalTambahan, pilihanPaketHaji + totalTambahan, totalBiayaSetelahInflasi, totalBiayaSetelahInflasi * totalDiskon, biayaAkhir);

    // Simulasi Menabung
    int bulanMenabung = (int) Math.ceil((biayaAkhir - uangMuka - simpananAwal) / tabunganPerBulan);
    int tahunMenabung = bulanMenabung / 12;
    int bulanSisa = bulanMenabung % 12;

    // Output Simulasi Menabung
    System.out.printf("\n### Simulasi Menabung ###\nSimpanan awal: Rp%d\nTabungan bulanan: Rp%d\nUang muka: Rp%d\nWaktu menabung: %d bulan (%d tahun %d bulan)\n",
        simpananAwal, tabunganPerBulan, uangMuka, bulanMenabung, tahunMenabung, bulanSisa);

    input.close();
  }
}
