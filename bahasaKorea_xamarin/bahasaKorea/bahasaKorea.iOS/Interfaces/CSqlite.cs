using bahasaKorea.Interfaces;
using bahasaKorea.iOS.Interfaces;
using Foundation;
using SQLite;
using System;
using System.Collections.Generic;
using System.IO;
using System.Text;
using Xamarin.Forms;

[assembly: Dependency(typeof(CSqlite))]
namespace bahasaKorea.iOS.Interfaces
{
    public class CSqlite : ISqlite
    {
        public CSqlite()
        {
        }

        public SQLiteConnection GetConnection()
        {
            var sqliteFilename = "KoreanDB.db";
            string documentsPath = Environment.GetFolderPath(Environment.SpecialFolder.Personal);
            string libraryPath = Path.Combine(documentsPath, "..", "Library");
            var path = Path.Combine(libraryPath, sqliteFilename);

            if (!File.Exists(path))
            {
                var existingDb = NSBundle.MainBundle.PathForResource("KoreanDB", "db");
                File.Copy(existingDb, path);
            }

            var conn = new SQLite.SQLiteConnection(path);
            return conn;
        }
    }
}
