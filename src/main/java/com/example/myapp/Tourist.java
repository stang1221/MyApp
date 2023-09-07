package com.example.myapp;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.Random;

public class Tourist extends Application {

    // declares private variables (scenes = cities)
    private Stage window;
    private Scene scene2;
    private Scene scene3;
    private Scene scene4;
    private Scene scene5;
    private Scene scene6;
    private Scene scene7;
    private Scene scene8;
    private Scene scene9;


    public static void main(String[] args) {

        launch(args);
    }

    @Override //application class

    public void start(Stage primaryStage) {

        // main stage initialized and scene 1 is set
        window = primaryStage;

        // Scene 1 (Main Menu)
        // This scene main menu, buttons objects that are set with their city names
        // if button is clicked window.setScene sets it as the active scene
        Label label1 = new Label("Welcome to the main menu!");

        Button button2 = new Button("Houston");
        button2.setOnAction(e -> window.setScene(scene2));

        Button button3 = new Button("Dallas");
        button3.setOnAction(e -> window.setScene(scene3));

        Button button4 = new Button("Fort Worth");
        button4.setOnAction(e -> window.setScene(scene4));

        Button button5 = new Button("San Antonio");
        button5.setOnAction(e -> window.setScene(scene5));

        Button button6 = new Button("El Paso");
        button6.setOnAction(e -> window.setScene(scene6));

        Button button7 = new Button("Arlington");
        button7.setOnAction(e -> window.setScene(scene7));

        Button button8 = new Button("Corpus Christi");
        button8.setOnAction(e -> window.setScene(scene8));

        Button button9 = new Button("Gallery");
        button9.setOnAction(e -> window.setScene(scene9));

        // How we set up our layout buttons at the top center and padding
        VBox layout1 = new VBox(20);

        layout1.setAlignment(Pos.TOP_CENTER);
        layout1.setPadding(new Insets(20));
        layout1.getChildren().addAll(label1);

        Scene scene1 = new Scene(layout1, 800, 700);

        Image imageFlag = new Image("texanflag.jpg");
        ImageView view1 = new ImageView(imageFlag);
        view1.setFitHeight(350);
        view1.setFitWidth(400);

        // Flowpane displays the texas flag image as well as the buttons
        FlowPane imagePane = new FlowPane();
        imagePane.setAlignment(Pos.BOTTOM_CENTER);
        imagePane.setVgap(20);
        imagePane.getChildren().addAll(view1);

        FlowPane buttonPane = new FlowPane();
        buttonPane.setAlignment(Pos.CENTER);
        buttonPane.setVgap(50);
        buttonPane.setHgap(20);
        buttonPane.getChildren().addAll(button2, button3, button4, button5, button6, button7, button8, button9);

        layout1.getChildren().addAll(buttonPane, imagePane);

        // Create the scenes for each city and assigns variables,
        // Each scene has CreateSceneWithButton called w/ specific params

        // Scene 2 Houston needs gridId, GridX, and GridY: HGX, 65, 97
        Label label2 = new Label("Houston");
        scene2 = createSceneWithButton(scene1, "HGX", 65, 97, "Houston", label2);


        // Scene 3 Dallas needs gridId, GridX, and GridY: FWD, 89, 104
        Label label3 = new Label("Dallas");
        scene3 = createSceneWithButton(scene1, "FWD", 89, 104, "Dallas", label3);


        // Scene 4 Fort Worth needs gridId, GridX, and GridY: FWD, 69, 103
        Label label4 = new Label("Fort Worth");
        scene4 = createSceneWithButton(scene1, "FWD", 69, 103, "Fort Worth", label4);


        // Scene 5 San Antonio needs gridId, GridX, and GridY: EWX, 126, 55
        Label label5 = new Label("San Antonio");
        scene5 = createSceneWithButton(scene1, "EWX", 126, 55, "San Antonio",label5);


        // Scene 6 El Paso needs gridId, GridX, and GridY: EPZ, 100, 56
        Label label6 = new Label("El Paso");
        scene6 = createSceneWithButton(scene1, "EPZ", 100, 56, "El Paso", label6);


        // Scene 7 Arlington needs gridId, GridX, and GridY: FWD, 78, 102
        Label label7 = new Label("Arlington");
        scene7 = createSceneWithButton(scene1, "FWD", 78, 102, "Arlington", label7);


        // Scene 8 Corpus Christi needs gridId, GridX, and GridY: CRP, 112, 34
        Label label8 = new Label("Corpus Christi");
        scene8 = createSceneWithButton(scene1, "CRP", 112, 34, "Corpus Christi", label8);

        scene9 = createGalleryScene(scene9);


        window.setScene(scene1);
        window.setTitle("Texas Loving");
        window.show();
    }

    // method used to fetch data for specific city
    // constructs weather API URL from Grid ID,X,Y
    // retrieves & parses JSON data
    private void fetchWeatherData(String gridId, int gridX, int gridY) {
        try {
            String address = "https://api.weather.gov/gridpoints/" +
                    gridId + "/" + gridX + "," + gridY + "/" + "forecast/hourly";

            URL url = new URL(address);
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(url);

            int temp = root.get("properties").get("periods").get(0).get("temperature").asInt();
            String wind = root.get("properties").get("periods").get(0).get("windSpeed").toString();
            String forecast = root.get("properties").get("periods").get(0).get("shortForecast").toString();

            displayWeatherInformation(temp, forecast, wind);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // method is used to fetch news data for a specific city
    // reads an API key from a properties file
    // constructs the API URL, and uses the Jackson library to retrieve and parse JSON data
    private void fetchNewsData(String name, int randomNumber) throws IOException {

        Properties properties = new Properties();
        try {
            FileInputStream fileInputStream = new FileInputStream("/Users/sophiatang/IdeaProjects/MyLovelyFXApp/src/main/java/com/example/mylovelyfxapp/config.properties");
            properties.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String apiKey = properties.getProperty("api.key");
        String address = "https://newsapi.org/v2/everything?q=" + name + "&apiKey=" + apiKey;

        URL url = new URL(address);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(url);

        String title = root.get("articles").get(randomNumber).get("title").toString();
        String description = root.get("articles").get(randomNumber).get("description").toString();

        displayNewsInformation(title, description);
    }


    // method displays weather information in an alert dialog box
    private void displayWeatherInformation(int temperature, String forecast, String wind) {

        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Weather Information");
        alert.setHeaderText("Weather");
        alert.setContentText("Temperature: " + temperature + " F\nForecast: " + forecast + "\nWind: " + wind);
        alert.showAndWait();
    }

    // method displays news information in an alert dialog box
    private void displayNewsInformation(String title, String description) {

        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("News Information");
        alert.setHeaderText("Latest News");
        alert.setContentText("Title: " + title + "\nDescription: " + description);
        alert.showAndWait();
    }


    //  method is used to create scenes for each city. It takes parameters like gridId, gridX, and gridY
    private Scene createSceneWithButton(Scene targetScene, String gridId, int gridX, int gridY, String location, Label cityLabel) {

        // Buttons created to display Back to Main Menu, Weather, News
        Button button = new Button("Go back to Main Menu");
        button.setOnAction(e -> window.setScene(targetScene));

        Button weatherButton = new Button("Weather");
        weatherButton.setOnAction(e -> fetchWeatherData(gridId, gridX, gridY));

        Button newsButton = new Button("News");
        newsButton.setOnAction(e -> {

            // This line generates a random integer between 0 (inclusive) and 10 (exclusive) and assigns it to the randomNumber variable.
            // The generated number is used to select a random article from the news data.
            try {
                Random random = new Random();
                int randomNumber = random.nextInt(10);
                fetchNewsData(location, randomNumber);
            } catch (IOException ex) {
                throw new RuntimeException(ex);

            }
        });


        VBox layout = new VBox(20);
        layout.setAlignment(Pos.TOP_CENTER);
        layout.setPadding(new Insets(20));

        Label descriptionLabel = new Label();
        descriptionLabel.setWrapText(true);


        // Set the paragraph based on the location

        String paragraph = "";

        if (location.equals("Houston")) {

            paragraph = "Houston, the vibrant metropolis nestled in the heart of Texas, is a city of remarkable diversity and captivating attractions. Boasting a rich blend of cultural heritage, cutting-edge innovation, and Southern charm, Houston offers an array of must-see attractions that make it an enticing destination for travelers.\n" +
                    "\n" +
                    "One of the top attractions in Houston is the Space Center Houston, the official visitor center of NASA's Johnson Space Center. Here, visitors can delve into the awe-inspiring world of space exploration, interact with exhibits, and even touch a moon rock. The center offers a glimpse into the past, present, and future of space travel, making it a must-visit for science enthusiasts and dreamers alike.\n" +
                    "\n" +
                    "For those seeking art and culture, the Museum District is an absolute gem. With 19 museums, including the Museum of Fine Arts, the Houston Museum of Natural Science, and the Contemporary Arts Museum, this district is a haven for art connoisseurs. The eclectic range of collections spans from ancient artifacts to contemporary masterpieces, ensuring there is something to captivate every visitor's imagination.\n" +
                    "\n" +
                    "Nature lovers are also well catered to in Houston, thanks to the expansive Hermann Park. Located near the Museum District, this urban oasis offers a variety of recreational activities and beautiful green spaces. Visitors can take leisurely strolls through the Japanese Garden, rent a pedal boat on the tranquil McGovern Lake, or visit the Houston Zoo, which houses a diverse range of animal species.\n" +
                    "\n" +
                    "Sports enthusiasts will find their fix in Houston's sports scene, with teams like the Houston Astros (MLB) and the Houston Rockets (NBA) captivating fans year-round. The passionate sports culture and state-of-the-art stadiums provide an unforgettable experience for sports lovers.\n" +
                    "\n" +
                    "Lastly, no visit to Houston would be complete without indulging in its renowned culinary scene. As a melting pot of cultures, the city offers an incredible range of international cuisines, from Tex-Mex and barbecue to Vietnamese and Ethiopian. Foodies will be delighted by the mouthwatering flavors and diverse dining options that reflect Houston's cosmopolitan atmosphere.\n" +
                    "\n" +
                    "In conclusion, Houston's must-see attractions cater to a wide range of interests, ensuring there is something for everyone. Whether exploring the wonders of space, immersing oneself in art and culture, enjoying the tranquility of nature, cheering on local sports teams, or savoring the delectable culinary offerings, a visit to Houston promises a memorable and diverse experience.";

        } else if (location.equals("Dallas")) {

            paragraph = "Dallas, the vibrant city in North Texas, is a destination filled with must-see attractions that captivate visitors from around the world. From iconic landmarks to cultural hotspots, Dallas offers a unique blend of history, arts, and entertainment, making it a compelling place to explore.\n" +
                    "\n" +
                    "One of the top attractions in Dallas is the Sixth Floor Museum at Dealey Plaza. Housed in the former Texas School Book Depository, this museum chronicles the life, assassination, and legacy of President John F. Kennedy. Visitors can walk through the exhibits, see historic artifacts, and gain insight into one of the most significant moments in American history.\n" +
                    "\n" +
                    "For those seeking a taste of Texan culture, the Dallas Arts District is a must-visit. Spanning 20 square blocks, it is the largest urban arts district in the United States. Here, visitors can explore world-class museums, including the Dallas Museum of Art, the Nasher Sculpture Center, and the Crow Museum of Asian Art. The district also hosts numerous performing arts venues, showcasing ballet, opera, and theater productions.\n" +
                    "\n" +
                    "Sports fans will find their passion ignited in Dallas, known for its fervent sports culture. The city is home to professional sports teams like the Dallas Cowboys (NFL) and the Dallas Mavericks (NBA). Attending a game at the AT&T Stadium or the American Airlines Center offers an exhilarating experience, immersing visitors in the electric atmosphere of live sports.\n" +
                    "\n" +
                    "No visit to Dallas would be complete without a visit to the historic Fort Worth Stockyards. Located just outside of Dallas, this living museum preserves the city's rich Western heritage. Visitors can witness cattle drives, explore authentic saloons, and even catch a rodeo. The Fort Worth Stockyards capture the spirit of the Old West and provide a glimpse into Texas' cowboy culture.\n" +
                    "\n" +
                    "Lastly, Dallas is renowned for its diverse culinary scene. From sizzling steakhouses and Tex-Mex joints to global cuisines and innovative food trucks, the city offers a feast for the taste buds. Food enthusiasts can indulge in mouthwatering flavors and discover the unique culinary creations that define Dallas' gastronomic landscape.\n" +
                    "\n" +
                    "In summary, Dallas beckons travelers with its must-see attractions and captivating experiences. Whether exploring history at the Sixth Floor Museum, immersing oneself in arts and culture at the Dallas Arts District, embracing the thrill of live sports, discovering Western heritage at the Fort Worth Stockyards, or indulging in its vibrant culinary scene, Dallas promises an unforgettable journey of exploration and entertainment.";

        } else if (location.equals("Fort Worth")) {

            paragraph = "Fort Worth, nestled in the heart of Texas, is a city steeped in Western heritage and brimming with captivating attractions that make it a must-visit destination. From historic landmarks to cultural treasures, Fort Worth offers a rich tapestry of experiences that will enchant visitors from all walks of life.\n" +
                    "\n" +
                    "One of the top attractions in Fort Worth is the Fort Worth Stockyards, a living museum that pays homage to the city's cowboy culture and Old West heritage. Here, visitors can witness thrilling cattle drives, explore authentic saloons, and even catch a rodeo. The Stockyards offer a unique opportunity to immerse oneself in the rich history and vibrant spirit of Texas.\n" +
                    "\n" +
                    "Art enthusiasts will be captivated by the Cultural District, home to renowned museums and galleries. The Kimbell Art Museum, featuring an exceptional collection of masterpieces from around the world, is a must-see for art lovers. The Modern Art Museum of Fort Worth and the Amon Carter Museum of American Art are also worth exploring, showcasing contemporary and American artworks, respectively. The Cultural District is a haven for those seeking artistic inspiration and cultural immersion.\n" +
                    "\n" +
                    "For a taste of the Wild West, the National Cowgirl Museum and Hall of Fame is a must-visit attraction. Dedicated to celebrating the achievements and contributions of women in the American West, this museum offers engaging exhibits and interactive displays that shed light on the stories of remarkable cowgirls.\n" +
                    "\n" +
                    "Sundance Square, located in downtown Fort Worth, is a vibrant entertainment district that beckons visitors with its charming blend of shops, restaurants, and live performances. Strolling along the lively streets, enjoying a delicious meal at a local eatery, or catching a show at one of the theaters is a delightful way to experience the city's energetic atmosphere.\n" +
                    "\n" +
                    "Lastly, no visit to Fort Worth is complete without savoring its legendary Texan cuisine. From mouthwatering barbecue joints to authentic Tex-Mex restaurants, the city offers a delectable array of culinary delights. Food enthusiasts can indulge in smoky flavors, savory meats, and flavorful Texan specialties that will leave a lasting impression.\n" +
                    "\n" +
                    "Therefore, Fort Worth's must-see attractions offer a captivating blend of history, art, culture, and Western charm. Whether experiencing the cowboy culture at the Stockyards, immersing oneself in the art scene of the Cultural District, exploring the achievements of remarkable women at the Cowgirl Museum, enjoying the vibrant atmosphere of Sundance Square, or indulging in the city's delicious cuisine, Fort Worth promises a memorable and enriching visit for every traveler.";

        } else if (location.equals("San Antonio")) {

            paragraph = "San Antonio is a destination filled with must-see attractions that offer a rich blend of history, culture, and natural beauty. From iconic landmarks to immersive experiences, San Antonio promises a memorable visit for travelers of all interests.\n" +
                    "\n" +
                    "At the heart of the city lies the iconic Alamo, a symbol of Texas' struggle for independence. The Alamo is a must-visit for history buffs, offering a glimpse into the famous battle that took place here in 1836. Visitors can explore the preserved mission grounds, learn about the heroes who fought for Texas, and gain insight into the state's rich heritage.\n" +
                    "\n" +
                    "Another must-see attraction in San Antonio is the scenic River Walk. This charming network of walkways along the San Antonio River winds through the downtown area and is lined with shops, restaurants, and lush greenery. Taking a leisurely stroll or a relaxing boat ride along the River Walk offers a unique perspective of the city and its vibrant atmosphere.\n" +
                    "\n" +
                    "For a cultural experience, the UNESCO World Heritage Site of the San Antonio Missions is a must-visit. These historic Spanish missions, including Mission San Jose and Mission Concepcion, showcase the architectural and cultural legacy of the region. Exploring the mission grounds and learning about the indigenous people and European settlers who shaped the area provide a fascinating glimpse into the city's past.\n" +
                    "\n" +
                    "San Antonio is also home to the vibrant Market Square, or El Mercado, the largest Mexican market in the United States. This lively and colorful market offers a vibrant atmosphere with shops selling traditional Mexican arts, crafts, clothing, and cuisine. Visitors can savor authentic Tex-Mex dishes, listen to live music, and immerse themselves in the rich culture of the region.\n" +
                    "\n" +
                    "Nature enthusiasts should not miss the opportunity to visit the Natural Bridge Caverns, a remarkable underground world of stunning rock formations. Guided tours take visitors deep into the caverns, where they can marvel at the intricate formations and learn about the geology and history of the area.\n" +
                    "\n" +
                    "San Antonio's must-see attractions offer a diverse range of experiences that showcase the city's rich history, vibrant culture, and natural wonders. Whether exploring the iconic Alamo, strolling along the scenic River Walk, immersing oneself in the cultural delights of Market Square, or venturing into the underground beauty of the Natural Bridge Caverns, San Antonio is a destination that promises an unforgettable journey of exploration and discovery.";

        }else if (location.equals("El Paso")) {

            paragraph = "El Paso, a city in the westernmost tip of Texas, offers a captivating blend of history, culture, and natural beauty. With its unique blend of Mexican and American influences, El Paso boasts a range of must-see attractions that make it a compelling destination for travelers.\n" +
                    "\n" +
                    "One of the top attractions in El Paso is the stunning Franklin Mountains State Park. As the largest urban park in the United States, it offers a haven for outdoor enthusiasts. Hiking, mountain biking, and rock climbing opportunities abound, allowing visitors to immerse themselves in the rugged beauty of the desert landscape. The park also provides breathtaking views of the city and the surrounding area.\n" +
                    "\n" +
                    "For a taste of the region's rich history, a visit to the Chamizal National Memorial is a must. This historic site commemorates the peaceful resolution of a border dispute between the United States and Mexico. The memorial features a museum, outdoor amphitheater, and beautifully landscaped grounds. Visitors can learn about the cultural heritage of the region, attend live performances, and explore the walking trails.\n" +
                    "\n" +
                    "The El Paso Museum of Art is another must-visit attraction, showcasing an impressive collection of European, Mexican, and American art. From ancient Egyptian artifacts to modern masterpieces, the museum offers a diverse range of artistic experiences. It also hosts rotating exhibitions, educational programs, and special events, making it a hub for art enthusiasts.\n" +
                    "\n" +
                    "For a unique cultural experience, a visit to El Paso's vibrant downtown district is essential. Here, visitors can explore the San Jacinto Plaza, a historic square adorned with sculptures, fountains, and beautiful landscaping. The plaza is surrounded by shops, restaurants, and bars, providing a lively atmosphere for locals and tourists alike.\n" +
                    "\n" +
                    "Additionally, El Paso offers a delectable culinary scene, influenced by both Mexican and American flavors. From savoring authentic Mexican cuisine at local taquerias to indulging in regional Tex-Mex specialties, food enthusiasts will be delighted by the diverse range of dining options.\n" +
                    "\n" +
                    "El Paso's must-see attractions offer a compelling mix of natural beauty, rich history, vibrant art, and cultural experiences. Whether hiking in the Franklin Mountains State Park, exploring the Chamizal National Memorial, immersing oneself in art at the El Paso Museum of Art, enjoying the lively downtown district, or savoring the unique flavors of the region, El Paso promises a memorable visit filled with exploration, discovery, and a rich cultural tapestry.";

        } else if (location.equals("Arlington")) {

            paragraph = "Whether you want to cheer on your team, shop to your heart’s content, get some thrills or just chill in the great outdoors, you’ll find something to love about Arlington. Located centrally between Dallas and Fort Worth, this bustling city is home to two professional teams (the Dallas Cowboys and Texas Rangers), world-class stadiums, and two spectacular amusement parks. Whether you want to escape to the scenic shores at Lake Arlington and explore River Legacy Park or indulge in some retail therapy – or a little bit of both – Arlington has you covered. During your stay, you’ll soon discover everything that makes Arlington a World of Wonderful. \n" +
                    "\n" +
                    " \n" +
                    "Upscale shops, top-in-class restaurants, and a host of entertainment can be found at Arlington Highlands. This outdoor mall has more than 50 stores and 250 restaurants, a movie theater, and an improv comedy club. \n" +
                    "\n" +
                    " \n" +
                    "Step into the Arlington Museum of Art to see astounding works by leading artists. This non-collecting museum showcases rotating exhibits of paintings, illustrations, sculptures, and other works by artists such as Andy Warhol, Jackson Pollock, and Pablo Picasso. Information regarding current and upcoming exhibits is available on the art museum’s website. \n" +
                    " \n" +
                    "Listen to everything from classic symphonies to contemporary jazz and iconic country at the recently renovated Arlington Music Hall. Arlington Music Hall is home to the Arlington Symphony but hosts a wide variety of music. Jazz musicians and Grammy winning artists such as Ricky Skaggs and Willie Nelson have performed there. \n" +
                    "\n" +
                    " \n" +
                    "AT&T Stadium is a world-class stadium home to “America’s Team,” the Dallas Cowboys. Cheer on the Cowboys with thousands of other silver and blue-clad fans from September to January, or catch a concert from an internationally renowned band throughout the year. Other big-name events, such as Monster Jam, the Professional Bull Rider Iron Cowboy Major, and Monster Energy Supercross are held at the stadium year-round. Much more than your average sports venue, AT&T Stadium also houses world-class art and offers guided art tours. Visit AT&T Stadium’s website for a full calendar of events. \n" +
                    "\n" +
                    " \n" +
                    "Savor a pint of hand-crafted craft beer at Division Brewing, the first locally-owned brewery in Arlington. A variety of brews are available on tap, including chocolate and coffee-flavored stouts, hoppy IPAs, and a sour that is fermented at warmer temperatures. ";

        }else if (location.equals("Corpus Christi")) {

            paragraph = "Coast like a Texan in the Gulf Coast Capital. Explore nine unique beaches in Corpus Christi and unleash your adventurous side by windsurfing, parasailing and kayaking out on the water. Craving inspiration in the outdoors? Try a paddleboard yoga class or horseback riding on the sand. Corpus Christi is also the center of iconic attractions like the Texas State Aquarium and USS Lexington. With diverse dining that suits every taste, Tex-Mex and seafood restaurants in Corpus Christi are special standouts and local chefs can even cook up your catch of the day. Whether you’re ready for a family vacation or getting away with friends, find hotels in Corpus Christi to fit every need. Come to Corpus Christi and go your own way. \n" +
                    "\n" +
                    "Beaches \n" +
                    "\n" +
                    "Corpus Christi's 100+ miles of beaches are the heart and soul of the coast. \n" +
                    "\n" +
                    "Corpus Christi's 100+ miles of beaches are the heart and soul of the coast. \n" +
                    "\n" +
                    "SUN, SURF AND SAND \n" +
                    "\n" +
                    "Miles of expansive beaches in Corpus Christi welcome you to come out and play. Go big with consistent swells that are perfect for surfing, or spend a day fishing from a pier. Families will love the beaches with smooth sands and gentle waves for all ages. However you define your perfect beach day, the largest coastal city in Texas has just what you’re looking for. \n" +
                    "\n" +
                    "Padre Island National Seashore \n" +
                    "\n" +
                    "The most significant undeveloped barrier island in the world is 30 miles south of Mustang Island State Park. \n" +
                    "\n" +
                    "The most significant undeveloped barrier island in the world is 30 miles south of Mustang Island… \n" +
                    "\n" +
                    "Spend an exhilarating day out on a pristine stretch of paradise that lies just minutes from downtown Corpus Christi. As the world’s largest remaining natural barrier island, this spectacular expanse includes 70 miles of uninterrupted national seashore. Roam among sandy beaches brushed by Gulf breezes, spot a diverse array of birds and other wildlife, and soak up the sun in this jewel of South Texas. \n" +
                    "\n" +
                    "Texas State Aquarium \n" +
                    "\n" +
                    "The idea for the Texas State Aquarium was conceptualized by its founders early on to focus on the species in the Gulf of Mexico and the Caribbean Sea. The exhibits would… \n" +
                    "\n" +
                    "The idea for the Texas State Aquarium was conceptualized by its founders early on to focus on the… \n" +
                    "\n" +
                    "TEXAS STATE AQUARIUM \n" +
                    "\n" +
                    "Plunge into an adventure that inspires visitors of all ages in this Corpus Christi aquarium. With playful dolphins, sea turtles, stingrays and many more sea creatures from all over the world, education and entertainment go hand in hand. Learn about marine conservation and immerse yourself in underwater worlds in the 4D theater ";
        }

        descriptionLabel.setText(paragraph);


        HBox buttonsLayout = new HBox(20);
        buttonsLayout.setAlignment(Pos.CENTER);
        buttonsLayout.getChildren().addAll(button, weatherButton, newsButton);
        layout.getChildren().addAll(cityLabel, buttonsLayout, descriptionLabel);


        return new Scene(layout, 800, 600);
    }

    private Scene createGalleryScene(Scene targetScene){
        Button button = new Button("Go back to Main Menu");

        button.setOnAction(e -> window.setScene(targetScene));
        //image pane for cities
        Image image1 = new Image("houstonfall.jpg");
        ImageView houston1 = new ImageView(image1);
        houston1.setFitHeight(75);
        houston1.setFitWidth(75);

        Image image2 = new Image("houstonferris.jpg");
        ImageView houston2 = new ImageView(image2);
        houston2.setFitHeight(75);
        houston2.setFitWidth(75);

        Image image3 = new Image("houstonmap.jpg");
        ImageView houston3 = new ImageView(image3);
        houston3.setFitHeight(75);
        houston3.setFitWidth(75);

        Image image4 = new Image("houstonnight.jpg");
        ImageView houston4 = new ImageView(image4);
        houston4.setFitHeight(75);
        houston4.setFitWidth(75);

        Image image5 = new Image("houstonriver.jpg");
        ImageView houston5 = new ImageView(image5);
        houston5.setFitHeight(75);
        houston5.setFitWidth(75);

        Image image6 = new Image("dallasball.jpg");
        ImageView dallas1 = new ImageView(image6);
        dallas1.setFitHeight(75);
        dallas1.setFitWidth(75);

        Image image7 = new Image("dallasfood.jpg");
        ImageView dallas2 = new ImageView(image7);
        dallas2.setFitHeight(75);
        dallas2.setFitWidth(75);

        Image image8 = new Image("dallasmap.jpg");
        ImageView dallas3 = new ImageView(image8);
        dallas3.setFitHeight(75);
        dallas3.setFitWidth(75);

        Image image9 = new Image("dallasskyline.jpg");
        ImageView dallas4 = new ImageView(image9);
        dallas4.setFitHeight(75);
        dallas4.setFitWidth(75);

        Image image10 = new Image("dallascherry.jpg");
        ImageView dallas5 = new ImageView(image10);
        dallas5.setFitHeight(75);
        dallas5.setFitWidth(75);

        Image image11 = new Image("fortworthcityline.jpg");
        ImageView fort1 = new ImageView(image11);
        fort1.setFitHeight(75);
        fort1.setFitWidth(75);

        Image image12 = new Image("fortworthculture.jpg");
        ImageView fort2 = new ImageView(image12);
        fort2.setFitHeight(75);
        fort2.setFitWidth(75);

        Image image13 = new Image("fortworthgardens.jpg");
        ImageView fort3 = new ImageView(image13);
        fort3.setFitHeight(75);
        fort3.setFitWidth(75);

        Image image14 = new Image("fortworthhorse.jpg");
        ImageView fort4 = new ImageView(image14);
        fort4.setFitHeight(75);
        fort4.setFitWidth(75);

        Image image15 = new Image("fortworthmap.jpg");
        ImageView fort5 = new ImageView(image15);
        fort5.setFitHeight(75);
        fort5.setFitWidth(75);

        Image image16 = new Image("sanantonioculture.jpg");
        ImageView san1 = new ImageView(image16);
        san1.setFitHeight(75);
        san1.setFitWidth(75);

        Image image17 = new Image("sanantoniofootball.jpg");
        ImageView san2 = new ImageView(image17);
        san2.setFitHeight(75);
        san2.setFitWidth(75);

        Image image18 = new Image("sanantonio3.jpg");
        ImageView san3 = new ImageView(image18);
        san3.setFitHeight(75);
        san3.setFitWidth(75);

        Image image19 = new Image("sanantonioriver.jpg");
        ImageView san4 = new ImageView(image19);
        san4.setFitHeight(75);
        san4.setFitWidth(75);

        Image image20 = new Image("sanantonioskyline.jpg");
        ImageView san5 = new ImageView(image20);
        san5.setFitHeight(75);
        san5.setFitWidth(75);

        Image image21 = new Image("ElPaso1.jpeg");
        ImageView elPaso1 = new ImageView(image21);
        elPaso1.setFitHeight(75);
        elPaso1.setFitWidth(75);

        Image image22 = new Image("elpasosky.jpg");
        ImageView elPaso2 = new ImageView(image22);
        elPaso2.setFitHeight(75);
        elPaso2.setFitWidth(75);

        Image image23 = new Image("ElPaso3.jpeg");
        ImageView elPaso3 = new ImageView(image23);
        elPaso3.setFitHeight(75);
        elPaso3.setFitWidth(75);

        Image image24 = new Image("ElPaso4.jpeg");
        ImageView elPaso4 = new ImageView(image24);
        elPaso4.setFitHeight(75);
        elPaso4.setFitWidth(75);

        Image image25 = new Image("ElPaso5.jpeg");
        ImageView elPaso5 = new ImageView(image25);
        elPaso5.setFitHeight(75);
        elPaso5.setFitWidth(75);

        Image image26 = new Image("Arlington1.jpg");
        ImageView arlington1 = new ImageView(image26);
        arlington1.setFitHeight(75);
        arlington1.setFitWidth(75);

        Image image27 = new Image("Arlington2.jpeg");
        ImageView arlington2 = new ImageView(image27);
        arlington2.setFitHeight(75);
        arlington2.setFitWidth(75);

        Image image28 = new Image("Arlington3.jpeg");
        ImageView arlington3 = new ImageView(image28);
        arlington3.setFitHeight(75);
        arlington3.setFitWidth(75);

        Image image29 = new Image("arlington4.jpg");
        ImageView arlington4 = new ImageView(image29);
        arlington4.setFitHeight(75);
        arlington4.setFitWidth(75);

        Image image30 = new Image("Arlington5.jpeg");
        ImageView arlington5 = new ImageView(image30);
        arlington5.setFitHeight(75);
        arlington5.setFitWidth(75);

        Image image31 = new Image("CorpusChristi1.jpeg");
        ImageView corpus1 = new ImageView(image31);
        corpus1.setFitHeight(75);
        corpus1.setFitWidth(75);

        Image image32 = new Image("CorpusChristi2.jpeg");
        ImageView corpus2 = new ImageView(image32);
        corpus2.setFitHeight(75);
        corpus2.setFitWidth(75);

        Image image33 = new Image("CorpusChristi3.jpeg");
        ImageView corpus3 = new ImageView(image33);
        corpus3.setFitHeight(75);
        corpus3.setFitWidth(75);

        Image image34 = new Image("CorpusChristi4.jpeg");
        ImageView corpus4 = new ImageView(image34);
        corpus4.setFitHeight(75);
        corpus4.setFitWidth(75);

        Image image35 = new Image("CorpusChristi5.jpeg");
        ImageView corpus5 = new ImageView(image35);
        corpus5.setFitHeight(75);
        corpus5.setFitWidth(75);



        HBox hBox1 = new HBox(20);
        Label houston = new Label("Houston");
        Label dallas = new Label("Dallas");
        Label sanAntonio = new Label("San Antonio");
        Label fortWorth = new Label("Fort Worth");
        Label arlington = new Label("Arlington");
        Label corpusChristi = new Label("Corpus Christi");
        Label elPaso = new Label("El Paso");

        hBox1.getChildren().addAll(houston,houston1,houston2,houston3,houston4,houston5);
        hBox1.setAlignment(Pos.CENTER);

        HBox hBox2 = new HBox(20);
        hBox2.getChildren().addAll(dallas,dallas1,dallas2,dallas3,dallas4,dallas5);
        hBox2.setAlignment(Pos.CENTER);

        HBox hBox3 = new HBox(20);
        hBox3.getChildren().addAll(sanAntonio,san1,san2,san3,san4,san5);
        hBox3.setAlignment(Pos.CENTER);

        HBox hBox4 = new HBox(20);
        hBox4.getChildren().addAll(fortWorth,fort1,fort2,fort3,fort4,fort5);
        hBox4.setAlignment(Pos.CENTER);

        HBox hBox5 = new HBox(20);
        hBox5.getChildren().addAll(arlington,arlington1,arlington2, arlington3, arlington4, arlington5);
        hBox5.setAlignment(Pos.CENTER);

        HBox hBox6 = new HBox(20);
        hBox6.getChildren().addAll(corpusChristi,corpus1,corpus2,corpus3,corpus4,corpus5);
        hBox6.setAlignment(Pos.CENTER);

        HBox hBox7 = new HBox(20);
        hBox7.setAlignment(Pos.CENTER);
        hBox7.getChildren().addAll(elPaso,elPaso1,elPaso2, elPaso3, elPaso4, elPaso5);

        VBox layoutGallery = new VBox(20);
        layoutGallery.setAlignment(Pos.CENTER_RIGHT);
        layoutGallery.getChildren().addAll(hBox1,hBox2,hBox3,hBox4,hBox5, hBox6, hBox7);


        return new Scene(layoutGallery,800,800);
    }
}

