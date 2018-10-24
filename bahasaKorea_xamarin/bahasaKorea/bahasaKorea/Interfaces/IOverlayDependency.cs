using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Xamarin.Forms;

namespace bahasaKorea.Interfaces
{
    public interface IOverlayDependency
    {
        void ShowOverlay(string message);
        void ShowOverlay(string message, Color backgroundColor, float backgroundOpacity);
        void HideOverlay();
    }
}
