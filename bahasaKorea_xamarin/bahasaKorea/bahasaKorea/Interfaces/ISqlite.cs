using System;
using SQLite;

namespace bahasaKorea.Interfaces
{
    public interface ISqlite
    {
        SQLiteConnection GetConnection();
    }
}
