package com.merp.jet.movie.model

data class Movie(
    val id: String,
    val title: String,
    val year: String,
    var genre: String,
    val actors: String,
    val plot: String,
    val poster: String,
    val images: List<String>,
    val rating: String
)

fun getMovies(): List<Movie> {
    return listOf(
        Movie(
            "1",
            "The Shawshank Redemption",
            "1994",
            "Drama",
            "Tim Robbins",
            "Two imprisoned men bond over several years, finding solace and eventual redemption through acts of common decency.",
            "https://fakeimg.pl/220x310/ff0000",
            listOf(
                "https://fakeimg.pl/220x310/ff0000",
                "https://fakeimg.pl/220x310/00ff00",
                "https://fakeimg.pl/220x310/0000ff",
                "https://fakeimg.pl/220x310/ff0000"
            ),
            "9.3"
        ),
        Movie(
            "2",
            "The Godfather",
            "1972",
            "Crime",
            "Marlon Brando",
            "The aging patriarch of an organized crime dynasty transfers control of his clandestine empire to his reluctant son.",
            "https://fakeimg.pl/220x310/ffff00",
            listOf(
                "https://fakeimg.pl/220x310/ffff00",
                "https://fakeimg.pl/220x310/ff0000",
                "https://fakeimg.pl/220x310/00ff00",
            ),
            "9.2"
        ),
        Movie(
            "3",
            "The Dark Knight",
            "2008",
            "Action",
            "Christian Bale",
            "When the menace known as the Joker wreaks havoc and chaos on the people of Gotham, Batman must accept one of the greatest psychological and physical tests of his ability to fight injustice.",
            "https://fakeimg.pl/220x310/ff00ff",
            listOf(
                "https://fakeimg.pl/220x310/ff00ff",
                "https://fakeimg.pl/220x310/ffffff",
                "https://fakeimg.pl/220x310/ffff00",
                "https://fakeimg.pl/220x310/fff00f",
            ),
            "9"
        ),
        Movie(
            "4",
            "Pulp Fiction",
            "1994",
            "Crime",
            "John Travolta",
            "The lives of two mob hit men, a boxer, a gangster's wife, and a pair of diner bandits intertwine in four tales of violence and redemption.",
            "https://fakeimg.pl/220x310/0000ff",
            listOf(
                "https://fakeimg.pl/220x310/0000ff",
                "https://fakeimg.pl/220x310/00ff00",
                "https://fakeimg.pl/220x310/0000ff",
                "https://fakeimg.pl/220x310/ff0000"
            ),
            "8.9"
        ),
        Movie(
            "5",
            "Forrest Gump",
            "1994",
            "Romance",
            "Tom Hanks",
            "The presidencies of Kennedy and Johnson, the events of Vietnam, Watergate, and other historical events unfold through the perspective of an Alabama man with an IQ of 75.",
            "https://fakeimg.pl/220x310/ff00ff",
            listOf(
                "https://fakeimg.pl/220x310/ff00ff",
                "https://fakeimg.pl/220x310/ffff00",
                "https://fakeimg.pl/220x310/ff0000",
                "https://fakeimg.pl/220x310/00ff00",
            ),
            "8.8"
        ),
        Movie(
            "6",
            "Inception",
            "2010",
            "Action",
            "Leonardo DiCapri",
            "A thief who steals corporate secrets through the use of dream-sharing technology is given the inverse task of planting an idea into the mind of a C.E.O.",
            "https://fakeimg.pl/220x310/f8f8f8",
            listOf(
                "https://fakeimg.pl/220x310/f8f8f8",
                "https://fakeimg.pl/220x310/00ff00",
                "https://fakeimg.pl/220x310/0000ff",
                "https://fakeimg.pl/220x310/ff0000"
            ),
            "2010"
        ),
        Movie(
            "7",
            "The Matrix",
            "1999",
            "Sci-Fi",
            "Laurence Fishburn",
            "A computer hacker learns from mysterious rebels about the true nature of his reality and his role in the war against its controllers.",
            "https://fakeimg.pl/220x310/f6f6f6",
            listOf(
                "https://fakeimg.pl/220x310/f6f6f6",
                "https://fakeimg.pl/220x310/00ff00",
                "https://fakeimg.pl/220x310/0000ff",
                "https://fakeimg.pl/220x310/ff0000"
            ),
            "1999"
        ),
        Movie(
            "8",
            "The Lord of the Rings: The Return of the King",
            "2003",
            "Adventure",
            "Elijah Wood",
            "Gandalf and Aragon lead the World of Men against Sauro's army to draw his gaze from Frodo and Sam as they approach Mount Doom with the One Ring.",
            "https://fakeimg.pl/220x310/ff00ff",
            listOf(
                "https://fakeimg.pl/220x310/ff00ff",
                "https://fakeimg.pl/220x310/ff00ff",
                "https://fakeimg.pl/220x310/ffff00",
                "https://fakeimg.pl/220x310/ff0000",
            ),
            "8.9"
        ),
        Movie(
            "9",
            "Interstellar",
            "2014",
            "Drama",
            "Matthew McMcNaught",
            "A team of explorers travel through a wormhole in space in an attempt to ensure humanity's survival.",
            "https://fakeimg.pl/220x310/ff0000",
            listOf(
                "https://fakeimg.pl/220x310/ff0000",
                "https://fakeimg.pl/220x310/00ff00",
                "https://fakeimg.pl/220x310/0000ff",
                "https://fakeimg.pl/220x310/ff0000"
            ),
            "9.3"
        ),
        Movie(
            "10",
            "Fight Club",
            "1999",
            "Drama",
            "Helena Bon ham Carter",
            "An insomniac office worker and a devil-may-care soap maker form an underground fight club that evolves into something much, much more.",
            "https://fakeimg.pl/220x310/ffff00",
            listOf(
                "https://fakeimg.pl/220x310/ffff00",
                "https://fakeimg.pl/220x310/ff0000",
                "https://fakeimg.pl/220x310/00ff00",
            ),
            "8.8"
        ),
        Movie(
            "11",
            "Gladiator",
            "2000",
            "Action",
            "Connie Nielsen",
            "A former Roman General sets out to exact vengeance against the corrupt emperor who murdered his family and sent him into slavery.",
            "https://fakeimg.pl/220x310/f8f8f8",
            listOf(
                "https://fakeimg.pl/220x310/f8f8f8",
                "https://fakeimg.pl/220x310/ffffff",
                "https://fakeimg.pl/220x310/ffff00",
                "https://fakeimg.pl/220x310/fff00f",
            ),
            "9.6"
        ),
    )
}