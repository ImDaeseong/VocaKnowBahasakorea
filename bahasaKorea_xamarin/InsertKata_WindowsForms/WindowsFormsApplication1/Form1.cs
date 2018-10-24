using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.Data.SQLite;
using System.IO;


namespace WindowsFormsApplication1
{
    public partial class Form1 : Form
    {
        private bool bFirst = false;

        private SQLiteConnection _connection;

        private void createdatabaseDaeseong()
        {
            String databaseName = "KoreanDB.db";
            if (File.Exists(databaseName))
            {
                _connection = new SQLiteConnection("Data Source=" + databaseName);
                _connection.Open();
            }
            else
            {
                SQLiteConnection.CreateFile(databaseName);
                var _connection = new SQLiteConnection("Data Source=" + databaseName);
                _connection.Open();
            }
        }

        private void createTableKatas()
        {
            try
            {
                string query = "CREATE TABLE Katas ( " +
                    "rIndex int," +
                    "KataKor varchar(200)," +
                    "KataIndo varchar(200)," +
                    "KataEng varchar(200));";
                SQLiteCommand cmd = new SQLiteCommand(query, _connection);
                cmd.ExecuteNonQuery();
            }
            catch (Exception ex)
            {
                Console.WriteLine(ex.Message.ToString());
            }
        }

        private void createTableHangul()
        {
            try
            {
                string query = "CREATE TABLE Hangul ( " +
                    "rIndex int," +
                    "KataFirst varchar(10)," +
                    "KataSecond varchar(10)," +
                    "KataEnd varchar(20));";
                SQLiteCommand cmd = new SQLiteCommand(query, _connection);
                cmd.ExecuteNonQuery();
            }
            catch (Exception ex)
            {
                Console.WriteLine(ex.Message.ToString());
            }
        }

        private void insertKatas(string query)
        {
            try
            {
                SQLiteCommand com = new SQLiteCommand(query, _connection);
                com.ExecuteNonQuery();
            }
            catch
            {
            }
        }

        private SQLiteDataReader reader(string query)
        {
            SQLiteCommand com = new SQLiteCommand(query, _connection);
            return com.ExecuteReader();
        }

        private void LoadKatas()
        {
            try
            {               
                SQLiteDataReader r = reader("SELECT * FROM Katas");
                while (r.Read())
                {
                    String s1 = r["rIndex"].ToString();
                    String s2 = r["KataKor"].ToString();
                    String s3 = r["KataIndo"].ToString();
                    String s4 = r["KataEng"].ToString();
                    //Console.WriteLine(s1, s2, s3, s4);
                    ListViewItem item = new ListViewItem();
                    item.Text = "";
                    item.SubItems.Add(s1);
                    item.SubItems.Add(s2);
                    item.SubItems.Add(s3);
                    item.SubItems.Add(s4);
                    lstView.Items.Add(item);
                }
            }
            catch
            {
            }

        }

        private void LoadHangul()
        {
            try
            {
                SQLiteDataReader r = reader("SELECT * FROM Hangul");
                while (r.Read())
                {
                    String s1 = r["rIndex"].ToString();
                    String s2 = r["KataFirst"].ToString();
                    String s3 = r["KataSecond"].ToString();
                    String s4 = r["KataEnd"].ToString();
                    //Console.WriteLine(s1, s2, s3, s4);
                    ListViewItem item = new ListViewItem();
                    item.Text = "";
                    item.SubItems.Add(s1);
                    item.SubItems.Add(s2);
                    item.SubItems.Add(s3);
                    item.SubItems.Add(s4);
                    lstView.Items.Add(item);
                }
            }
            catch
            {
            }

        }

        private string NullVal(object src, string Value)
        {
            if (src != null)
                return src.ToString();
            return Value;
        }

        private string QStr(string sValue)
        {
            return "'" + sValue.Replace("'", "''") + "'";
        }

        private void ReadFile()
        {
            StreamReader reader = new StreamReader("kata.txt");
            using (reader)
            {
                string rIndex;
                string KataKor;
                string KataIndo;
                string KataEng;
                
                string line = reader.ReadLine();
                while (line != null)
                {
                    string[] value = line.Split('|');
                    rIndex = value[0];
                   
                    try
                    {
                        KataKor = NullVal(value[1], "").Replace("\"", "").Replace("\r", "").Replace("\n", "").Replace("\t", "").Trim();
                    }
                    catch { KataKor = ""; }

                    try
                    {
                        KataIndo = NullVal(value[2], "").Replace("\"", "").Replace("\r", "").Replace("\n", "").Replace("\t", "").Trim();
                    }
                    catch { KataIndo = ""; }                    

                    try
                    {
                        KataEng = NullVal(value[3], "").Replace("\"", "").Replace("\r", "").Replace("\n", "").Replace("\t", "").Trim();
                    }
                    catch { KataEng = "";}

                    //string val = string.Format("{0}|{1}|{2}|{3}", rIndex, KataKor, KataIndo, KataEng);
                    //Console.WriteLine(val);

                    string query = string.Format("INSERT INTO Katas (rIndex, KataKor, KataIndo, KataEng) VALUES ({0},{1},{2},{3});", rIndex, QStr(KataKor), QStr(KataIndo), QStr(KataEng));
                    insertKatas(query);

                    line = reader.ReadLine();
                }
            }
        }

        public Form1()
        {
            InitializeComponent();

            lstView.View = View.Details;
            lstView.GridLines = true;
            lstView.FullRowSelect = true;
            lstView.HeaderStyle = ColumnHeaderStyle.Clickable;
            lstView.CheckBoxes = true;
            lstView.OwnerDraw = true;

            lstView.Columns.Add("", 25, HorizontalAlignment.Left);
            lstView.Columns.Add("번호", 50, HorizontalAlignment.Left);           
            lstView.Columns.Add("한국어", 450, HorizontalAlignment.Left);
            lstView.Columns.Add("인도네시아어", 500, HorizontalAlignment.Left);
            lstView.Columns.Add("영어", 450, HorizontalAlignment.Left);
            
            createdatabaseDaeseong();
                        
            
            //처음 DB 생성후 데이터 insert(최초 사용시만 사용)
            if (bFirst)
            {
                createTableKatas();
                createTableHangul();
                ReadFile();
            }

            //LoadHangul();
            LoadKatas();
        }

        private void InitChkBox()
        {
            for (int i = 0; i < lstView.Items.Count; i++)
                lstView.Items[i].Checked = false;
        }

        private void lstView_DrawColumnHeader(object sender, DrawListViewColumnHeaderEventArgs e)
        {
            if (e.ColumnIndex == 0)
            {
                e.DrawBackground();
                bool value = false;
                try
                {
                    value = Convert.ToBoolean(e.Header.Tag);
                }
                catch (Exception)
                {
                }
                CheckBoxRenderer.DrawCheckBox(e.Graphics,
                    new Point(e.Bounds.Left + 4, e.Bounds.Top + 4),
                    value ? System.Windows.Forms.VisualStyles.CheckBoxState.CheckedNormal :
                    System.Windows.Forms.VisualStyles.CheckBoxState.UncheckedNormal);
            }
            else
            {
                e.DrawDefault = true;
            }
        }

        private void lstView_DrawItem(object sender, DrawListViewItemEventArgs e)
        {
            e.DrawDefault = true;
        }

        private void lstView_DrawSubItem(object sender, DrawListViewSubItemEventArgs e)
        {
            e.DrawDefault = true;
        }

        private void lstView_SelectedIndexChanged(object sender, EventArgs e)
        {
            if (lstView.SelectedItems.Count > 0)
            {
                string num = lstView.SelectedItems[0].SubItems[1].Text.ToString();
                string katakor = lstView.SelectedItems[0].SubItems[2].Text.ToString();
                string kataindo = lstView.SelectedItems[0].SubItems[3].Text.ToString();
                string KataEng = lstView.SelectedItems[0].SubItems[4].Text.ToString();
               
                txtKor.Text = katakor;
                txtIndo.Text = kataindo;
                txtEng.Text = KataEng;
            }
            else
            {
                txtKor.Text = "";
                txtIndo.Text = "";
                txtEng.Text = "";
            }            
        }
        
        private void lstView_ColumnClick(object sender, ColumnClickEventArgs e)
        {
            if (e.Column == 0)
            {
                bool value = false;
                try
                {
                    value = Convert.ToBoolean(lstView.Columns[e.Column].Tag);
                }
                catch (Exception)
                {
                }
                lstView.Columns[e.Column].Tag = !value;
                foreach (ListViewItem item in lstView.Items)
                    item.Checked = !value;

                lstView.Invalidate();
            }
        }

      
        private void btnUpdate_Click(object sender, EventArgs e)
        {
            if (lstView.SelectedItems.Count > 0)
            {
                string num = lstView.SelectedItems[0].SubItems[1].Text.ToString();

                string query = string.Format("UPDATE Katas SET KataKor={0}, KataIndo={1}, KataEng={2} where rIndex = {3}", QStr(txtKor.Text), QStr(txtIndo.Text), QStr(txtEng.Text), num);
                insertKatas(query);
            }

            //lstView.Items.Clear();
            //LoadKatas();
        }

        private void btnDelete_Click(object sender, EventArgs e)
        {            
            for (int i = lstView.Items.Count - 1; i >= 0; i--)
            {
                if (lstView.Items[i].Checked)
                {
                    ListViewItem listViewItem = lstView.Items[i];
                    string num = listViewItem.SubItems[1].Text.ToString();
                    string katakor = listViewItem.SubItems[2].Text.ToString();
                    string kataindo = listViewItem.SubItems[3].Text.ToString();
                    string KataEng = listViewItem.SubItems[4].Text.ToString();
                   
                    lstView.Items.RemoveAt(i);
                    string query = string.Format("DELETE FROM Katas where rIndex = {0}", num);
                    insertKatas(query);
                }
            }
        }

        private void btnAdd_Click(object sender, EventArgs e)
        {
            Boolean bIsExist = false;
            string query = string.Format("SELECT * FROM Katas where KataIndo = {0}", QStr(txtIndo.Text));
            SQLiteDataReader r = reader(query);
            while (r.Read())
            {
                bIsExist = true;
                break;
            }

            if (bIsExist)
                return;


            int rIndex = lstView.Items.Count + 1; 
            query = string.Format("INSERT INTO Katas (rIndex, KataKor, KataIndo, KataEng) VALUES ({0},{1},{2},{3});", rIndex, QStr(txtKor.Text), QStr(txtIndo.Text), QStr(txtEng.Text) );
            insertKatas(query);

            ListViewItem item = new ListViewItem();
            item.Text = "";
            item.SubItems.Add(rIndex.ToString());
            item.SubItems.Add(txtKor.Text);
            item.SubItems.Add(txtIndo.Text);
            item.SubItems.Add(txtEng.Text);
            lstView.Items.Add(item);

            txtKor.Text = "";
            txtIndo.Text = "";
            txtEng.Text = "";

        }
        

    }
}
