/*
 *  Copyright 2023 The original authors
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package dev.morling.onebrc;

import java.io.*;
import java.util.*;

public class CalculateAverage_hellonico {
    public static void main(String[] args) throws IOException {
        String line;
        HashMap<String, ArrayList<Double>> tempsByCity = new HashMap<>();

        BufferedReader br = new BufferedReader(new FileReader("./measurements.txt"));
        while ((line = br.readLine()) != null) {
            if (line.charAt(0) == '#')
                continue;
            String[] parts = line.split(";");

            if (tempsByCity.containsKey(parts[0])) {
                tempsByCity.get(parts[0]).add(Double.parseDouble(parts[1]));
            }
            else {
                ArrayList<Double> tempList = new ArrayList<>();
                tempList.add(Double.parseDouble(parts[1]));
                tempsByCity.put(parts[0], tempList);
            }
        }

        TreeMap<String, String> results = new TreeMap<>();

        for (String city : tempsByCity.keySet()) {
            double sum = 0;
            double min = Double.MAX_VALUE;
            double max = Double.MIN_VALUE;

            ArrayList<Double> list = tempsByCity.get(city);

            for (double temp : list) {
                sum += temp;
                if (temp < min)
                    min = temp;
                if (temp > max)
                    max = temp;
            }

            results.put(city, String.format("%.1f/%.1f/%.1f", min, sum / list.size(), max));
        }

        for (String city : results.keySet()) {
            System.out.println(city + "=" + results.get(city));
        }
    }
}
