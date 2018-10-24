using SQLite;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace bahasaKorea.Modal
{
    [Table("Katas")]
    public class KataItems
    {
        public KataItems()
        {
        }

        [PrimaryKey, AutoIncrement]
        public int rIndex { get; set; }
        public string KataKor { get; set; }
        public string KataIndo { get; set; }
        public string KataEng { get; set; }
    }
}
