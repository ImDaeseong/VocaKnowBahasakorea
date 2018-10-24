using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

using Android.App;
using Android.Content;
using Android.OS;
using Android.Runtime;
using Android.Views;
using Android.Widget;
using bahasaKorea.Interfaces;
using bahasaKorea.Droid.Interfaces;
using Xamarin.Forms;
using SQLite;
using System.IO;

[assembly: Dependency(typeof(CSqlite))]
namespace bahasaKorea.Droid.Interfaces
{
    public class CSqlite : ISqlite
    {
        public CSqlite()
        {
        }

        public SQLiteConnection GetConnection()
        {
            var sqliteFilename = "KoreanDB.db";
            string documentsPath = System.Environment.GetFolderPath(System.Environment.SpecialFolder.Personal);
            var path = Path.Combine(documentsPath, sqliteFilename);

            if (!File.Exists(path))
            {
                CopyDatabaseIfNotExists(path, sqliteFilename);
            }

            var conn = new SQLite.SQLiteConnection(path);
            return conn;
        }

        private static void CopyDatabaseIfNotExists(string dbPath, string sqliteFilename)
        {
            using (var br = new BinaryReader(Android.App.Application.Context.Assets.Open(sqliteFilename)))
            {
                using (var bw = new BinaryWriter(new FileStream(dbPath, FileMode.Create)))
                {
                    byte[] buffer = new byte[2048];
                    int length = 0;
                    while ((length = br.Read(buffer, 0, buffer.Length)) > 0)
                    {
                        bw.Write(buffer, 0, length);
                    }
                }
            }
        }

    }
}