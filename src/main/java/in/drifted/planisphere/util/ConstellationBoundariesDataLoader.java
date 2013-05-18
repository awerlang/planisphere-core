package in.drifted.planisphere.util;

import java.awt.geom.Point2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public final class ConstellationBoundariesDataLoader implements Serializable {

    private List<Point2D> constellationBoundaryList;

    public ConstellationBoundariesDataLoader(String filePath) throws IOException {

        constellationBoundaryList = new ArrayList();

        InputStream inputStream = getClass().getResourceAsStream(filePath);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "ASCII"));
        try {
            Point2D coordLast = new Point2D.Double();
            String strLine;
            while ((strLine = reader.readLine()) != null) {
                if (!strLine.isEmpty()) {
                    String[] values = strLine.split("\t");
                    Point2D coord = new Point2D.Double();
                    coord.setLocation(Double.parseDouble(values[1]) / 1000.0D, Double.parseDouble(values[2]) / 100.0D);
                    if (values[0].equals("1")) {
                        this.constellationBoundaryList.add(coordLast);
                        this.constellationBoundaryList.add(coord);
                    }
                    coordLast = coord;
                }
            }
        } finally {
            reader.close();
        }
        inputStream.close();
    }

    public List<Point2D> getConstellationBoundaryList() {
        return constellationBoundaryList;
    }
}