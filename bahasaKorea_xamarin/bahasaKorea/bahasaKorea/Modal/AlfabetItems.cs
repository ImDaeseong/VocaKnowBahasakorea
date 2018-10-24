using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace bahasaKorea.Modal
{
    public class AlfabetItems
    {
        public AlfabetItems()
        {
        }

        public AlfabetItems(string KataKor, string Lembutlidah, string BuatKan)
        {
            this.KataKor = KataKor;
            this.Lembutlidah = Lembutlidah;
            this.BuatKan = BuatKan;
        }

        public string KataKor { get; set; }
        public string Lembutlidah { get; set; }
        public string BuatKan { get; set; }

    }
}
