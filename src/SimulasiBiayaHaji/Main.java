package SimulasiBiayaHaji;

import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);

    // Konstanta Paket Haji dan Tambahan Opsional
    final double[] PAKET_HAJI = { 40000000, 70000000, 120000000 };
    final double[] OPSIONAL = { 5000000, 10000000, 7000000 };
    final double INFLASI = 0.05;
    final double SUBSIDI_USIA_LANJUT = 0.10;
    final double DISKON_UANG_MUKA = 2000000;

    // Input Data
    System.out.print(
        "Pilih paket haji:\n1. Reguler (Rp40.000.000)\n2. Plus (Rp70.000.000)\n3. VIP (Rp120.000.000)\nMasukan pilihan Anda (1-3): ");
    double pilihanPaketHaji = PAKET_HAJI[input.nextInt() - 1];

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
      totalTambahan += OPSIONAL[Integer.parseInt(tambahan) - 1];
    }

    // Diskon/Subsidi
    double totalDiskon = 0;
    if (usia >= 60)
      totalDiskon += SUBSIDI_USIA_LANJUT;
    if (uangMuka >= 10000000)
      totalDiskon += DISKON_UANG_MUKA;

    // Hitung Biaya Setelah Inflasi
    for (int i = 1; i <= tahun; i++) {
      pilihanPaketHaji += pilihanPaketHaji * INFLASI;
      totalTambahan += totalTambahan * INFLASI;
    }
    double totalBiayaSetelahInflasi = pilihanPaketHaji + totalTambahan;

    // Hitung Biaya Akhir
    double biayaAkhir = totalBiayaSetelahInflasi - (totalBiayaSetelahInflasi * totalDiskon);

    // Output Simulasi Biaya Haji
    System.out.println("\n### Simulasi Biaya Haji ###");
    System.out.println("Paket yang dipilih: Rp" + pilihanPaketHaji);
    System.out.println("Biaya tambahan: Rp" + totalTambahan);
    System.out.println("Total biaya awal: Rp" + (pilihanPaketHaji + totalTambahan));
    System.out.println("Total biaya setelah inflasi: Rp" + totalBiayaSetelahInflasi);
    System.out.println("Diskon/Subsidi: Rp" + (totalBiayaSetelahInflasi * totalDiskon));
    System.out.println("Biaya akhir setelah diskon/subsidi: Rp" + biayaAkhir);

    // Simulasi Menabung
    int bulanMenabung = (int) Math.ceil((biayaAkhir - uangMuka - simpananAwal) / tabunganPerBulan);
    int tahunMenabung = bulanMenabung / 12;
    int bulanSisa = bulanMenabung % 12;

    // Output Simulasi Menabung
    System.out.println("\n### Simulasi Menabung ###");
    System.out.println("Simpanan awal: Rp" + simpananAwal);
    System.out.println("Tabungan bulanan: Rp" + tabunganPerBulan);
    System.out.println("Uang muka: Rp" + uangMuka);
    System.out.println("Waktu menabung: " + bulanMenabung + " bulan (" + tahunMenabung + " tahun " + bulanSisa + " bulan)");

    input.close();
  }
}
