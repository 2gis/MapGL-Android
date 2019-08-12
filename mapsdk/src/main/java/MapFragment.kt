import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment


private val initPageHtml = """
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <meta http-equiv="X-UA-Compatible" content="ie=edge" />
        <title>Blank</title>
        <style>
            html,
            body,
            #map {
                margin: 0;
                width: 100%;
                height: 100%;
                overflow: hidden;
            }
            .jakarta-hover {
                cursor: pointer;
            }
            .jakarta-rotating,
            .jakarta-inertia {
                cursor: move;
            }
            .jakarta-dragging {
                cursor: grabbing;
            }
        </style>
    </head>
    <body>
        <div id="map"></div>
        <script src="https://mapgl.2gis.com/api/js"></script>
        <script>
        
        
        
        
const container = document.getElementById('map');
window.map = new J.Map(container, {
    center: [37.618317, 55.750574],
    zoom: 16,
    minZoom: 2,
});

window.addEventListener('resize', () => window.map.invalidateSize());


        </script>
    </body>
</html>
"""

class MapFragment: Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }
}