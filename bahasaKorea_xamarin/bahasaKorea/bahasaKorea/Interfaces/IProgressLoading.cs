using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace bahasaKorea.Interfaces
{
    public interface IProgressLoading
    {
        void Show(string title = "pemuatan data...");

        void Hide();
    }
}
