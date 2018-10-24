using bahasaKorea.Interfaces;
using bahasaKorea.Modal;
using SQLite;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Xamarin.Forms;

namespace bahasaKorea.SingleTurn
{
    public class DBSqlite
    {
        private static DBSqlite selfInstance = null;
        public static DBSqlite getInstance
        {
            get
            {
                if (selfInstance == null) selfInstance = new DBSqlite();
                return selfInstance;
            }
        }

        static object mlock = new object();
        SQLiteConnection sqLiteConnection;

        public DBSqlite()
        {
            sqLiteConnection = DependencyService.Get<ISqlite>().GetConnection();
            sqLiteConnection.CreateTable<KataItems>();

        }

        public IEnumerable<KataItems> GetItems()
        {
            lock (mlock)
            {
                return (from i in sqLiteConnection.Table<KataItems>() select i).ToList();
            }
        }

        public IEnumerable<KataItems> GetSearchKata(string sSearch)
        {
            lock (mlock)
            {
                var qry = sqLiteConnection.Query<KataItems>(String.Format("SELECT * FROM [Katas] WHERE [KataKor] LIKE \"{0}%\"", sSearch));
                if (qry.Count > 0)
                {
                    return sqLiteConnection.Query<KataItems>(String.Format("SELECT * FROM [Katas] WHERE [KataKor] LIKE \"{0}%\"", sSearch));
                }

                qry = sqLiteConnection.Query<KataItems>(String.Format("SELECT * FROM [Katas] WHERE [KataIndo] LIKE \"{0}%\"", sSearch));
                if (qry.Count > 0)
                {
                    return sqLiteConnection.Query<KataItems>(String.Format("SELECT * FROM [Katas] WHERE [KataIndo] LIKE \"{0}%\"", sSearch));
                }

                qry = sqLiteConnection.Query<KataItems>(String.Format("SELECT * FROM [Katas] WHERE [KataEng] LIKE \"{0}%\"", sSearch));
                if (qry.Count > 0)
                {
                    return sqLiteConnection.Query<KataItems>(String.Format("SELECT * FROM [Katas] WHERE [KataEng] LIKE \"{0}%\"", sSearch));
                }
                return null;
            }
        }

    }
}
