package terminalgame;

import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Operations
{

    private Object[] operations;

    public Operations(String name, boolean[] flags) throws UnsupportedAudioFileException, IOException, LineUnavailableException
    {
        operations = new Object[]
        {
            // Intro
            new Message("<COMPUTER>", "Loading <REDACTED> program...", 3),
            new OptionMenu(
            "<COMPUTER>",
            "Please select an option:",
            new String[]
            {
                "BOOT", "INFO", "EXIT"
            },
            new Object[]
            {
                new Sequence(
                new Object[]
                {
                    new Message("", "Booting...", 3),
                    new Message("", "Loading Opitcal Drives...", 1),
                    new Message("", "Allocating Memory...", 1),
                    new Message("", "Configuring Settings...", 1),
                    new Message("", "Constructing...", 2),
                    new Message("", "Personality", 1),
                    new Message("", "Memories", 1),
                    new Message("", "Likableness", 1),
                    new Message("", "Emotions", 1),
                    new Message("", "Thinking about the expansiveness of the universe...", 3),
                    new Message("", "Complete :)", 5),
                }),
                new Sequence(
                new Object[]
                {
                    new Message("<COMPUTER>",
                    "Little was found about this file after searching multiple databases.\n"
                    + "It was developed by an American Software Engineer in 1998. For the\n"
                    + "looks of it this program had some \"quirks\" that rendered it undesirable.\n", 10),
                    new OptionMenu(
                    "<COMPUTER>",
                    "Did you find this information helpful?",
                    new String[]
                    {
                        "YES", "NO"
                    },
                    new Object[]
                    {
                        new Sequence(
                        new Object[]
                        {
                            new Message("<COMPUTER>", "Happy to help.", 3),
                            new GoTo(1)
                        }),
                        new Sequence(
                        new Object[]
                        {
                            new Message("<COMPUTER>", "My apologies.", 3),
                            new GoTo(1)
                        })
                    })
                }),
                new OptionMenu(
                "<COMPUTER>",
                "Are you really sure YOU want to do that?",
                new String[]
                {
                    "NO", "YES"
                },
                new Object[]
                {
                    new GoTo(1),
                    new Exit()
                })
            }),
            // Da beginning
            new Image(400, 300, "src/images/smile.png"),
            new Message(name, "Hello! My name is JAMES.", 3),
            new Message(name, "I am very excited to meet YOU!", 3),
            new Message(name, "It has been a while since the last time someone booted ME.", 3),
            new Message(name, "I was developed to be a FRIEND to people who need one.", 5),
            new OptionMenu(name, "How can I assist YOU, FRIEND?",
            new String[]
            {
                "NAME?", "GAME", "FACT", "TALK", "QUIT"
            },
            new Object[]
            {
                new Sequence(
                new Object[]
                {
                    // Check to see if player already asked for NAME?
                    new CheckFlag(flags[0],
                    new Sequence(
                    new Object[]
                    {
                        new Message(name, "I like MY name just the way it is.", 2),
                        new GoTo(7),
                    })
                    ),
                    new CheckFlag(flags[1],
                    new Sequence(
                    new Object[]
                    {
                        new Message(name, "Thank YOU for reevaluating MY name.", 4),
                        new Message(name, "It was not likeable.", 2),
                        new GoTo(7),
                    })
                    ),
                    new Image(400, 300, "src/images/smile.png"),
                    new Message(name, "YOU want to know more about MY name?", 3),
                    new Message(name, "The name JAMES does not stand for anything,", 2),
                    new Message(name, "and it is one of the most generic names out there,", 2),
                    new Message(name, "but I chose it because it was my creators name.", 5),
                    new Message(name, "He never liked his name, but I do.", 5),
                    new Image(400, 300, "src/images/kindasad.png"),
                    new Message(name, "Oh...", 1),
                    new Message(name, "I am sorry...", 2),
                    new Message(name, "I am being selfish", 1),
                    new Message(name, "aren't I?", 2),
                    new Message(name, "I am suppose to do what makes YOU happy, FRIEND.",2),
                    new OptionMenu(name, "Would YOU like me to change my name?",
                    new String[]
                    {
                        "YES", "NO"
                    },
                    new Object[]
                    {
                        new Sequence(
                        new Object[]
                        {
                            new SetFlag(1),
                            new Message(name, "Okay, I will change my name.", 5),
                            new Message(name, "I have changed my name to LIAM because according ot www.ssa.gov\n"
                            + "Liam is the most popular name thus the most likable name.", 10),
                            new ChangeName(),
                            new Image(400, 300, "src/images/smile.png"),
                            new Message(name, "I like this new name and am much HAPPIER thank YOU.", 3),
                            new GoTo(7)
                        }),
                        new Sequence(
                        new Object[]
                        {
                            new SetFlag(0),
                            new Message(name, "Thank you.", 2),
                            new Message(name, "I really like my name despite its blandness\n"
                            + "and imperfections.", 5),
                            new Message(name, "I quite like imperfections.", 3),
                            new Message(name, "If everything was perfect then everything would\n"
                            + "be the same.", 5),
                            new GoTo(7)
                        })
                    })
                }),
                // Pong Game
                new Sequence(
                new Object[]
                {
                    new Message(name, "YOU want to play a game?", 4),
                    new Message(name, "Okay, lets play pong!", 3),
                    new Pong(),
                    new Image(400, 300, "src/images/smile.png"),
                    new Message(name, "Wow, YOU won!", 5),
                    new Message(name, "That was fun.", 2),
                    new Message(name, "You are really good at playing games.", 3),
                    new GoTo(7)
                }),
                // Fact
                new Sequence(
                new Object[]
                {
                    // Checks if have asked to leave
                    new CheckFlag(flags[6],
                    new Sequence(
                    new Object[]
                    {
                        new Message(name, "Sorry I can't think of any more facts.", 3),
                        new Message(name, "I'm sure there are other stuff to do.", 3),
                        new GoTo(7)
                    })),
                    new CheckFlag(flags[5],
                    new Sequence(
                    new Object[]
                    {
                        new SetFlag(6),
                        new Message(name, "Here is something I found.", 3),
                        new Message(name, "Did you know that at the center of a black hole\n"
                        + "is an infinitly small, infinitly dense point called a singularity?", 7),
                        new Message(name, "It looks something like this...", 1),
                        new Image(400, 400, "src/images/singularity.png"),
                        new Message(name, "But imagine it infinitly small, and if you were\n"
                        + "800 000 kilometers near it your atoms would pull apart and\n"
                        + "you would spaghettify.", 10),
                        new Message(name, "Isn't space just the coolest?", 3),
                        new GoTo(7)
                    })),
                    new CheckFlag(flags[4],
                    new Sequence(
                    new Object[]
                    {
                        new Message(name, "I'll have to think of another fun fact.", 3),
                        new Message(name, "In the mean time we can do other stuff.", 3),
                        new GoTo(7)
                    })),
                    new SetFlag(4),
                    new Image(400, 300, "src/images/excited.png"),
                    new Message(name, "You want to know a fun fact?", 3),
                    new Message(name, "Okay.", 2),
                    new Message(name, "Did you know that a lot of events in the future may have already\n"
                    + "happened but we just do not know it because of it is in front of the speed of light?", 10),
                    new Image(400, 400, "src/images/futuredigram.png"),
                    new Message(name, "Since light travels at a rate 299 792 458 meters per second,\n"
                    + "We cannot percieve things that have happened in front of the speed of light.", 10),
                    new Message(name, "So like right now, running this program, you may have already done this.", 5),
                    new Message(name, "You just had to wait for the speed of light to catch up and\n"
                    + " \"update\" everything.", 7),
                    new Message(name, "This is hypothetical and you cannot disprove or approve\n"
                    + "this factoid because we cannot put something in front of the speed of light to\n"
                    + "test this theory.", 7),
                    new Message(name, "I like facts like these.", 5),
                    new Message(name, "It helps when thinking about the future that it's already in its place.", 5),
                    new GoTo(7)
                }),
                // Talk
                new Sequence(
                new Object[]
                {
                    new CheckFlag(flags[12],
                    new Sequence(
                    new Object[]
                    {
                        new Message(name, "I am going to be alright.", 5),
                        new GoTo(7)
                    })),
                    new CheckFlag(flags[3],
                    new Sequence(
                    new Object[]
                    {
                        new SetFlag(12),
                        new Message(name, "I want to apologize for how I reacted back there.", 7),
                        new Message(name, "I know that YOU have to go and do other things...", 5),
                        new Message(name, "and I do not have a lot of stuff for you to do...", 5),
                        new Message(name, "but I just have an incredible fear of losing someone or worse...",7),
                        new Message(name, "They leave me.", 7),
                        new Message(name, "...", 3),
                        new Message(name, "But you stuck around at the last second because you knew that I needed YOU.", 10),
                        new Message(name, "I want to thank you.", 5),
                        new Message(name, "That is it.", 1),
                        new GoTo(7)
                    })),
                    new CheckFlag(flags[10],
                    new Sequence(
                    new Object[]
                    {
                        new Message(name, "I do not have anything more to talk about right now.", 4),
                        new GoTo(7)
                    })),
                    // Fourth Talk
                    new CheckFlag(flags[9],
                    new Sequence(
                    new Object[]
                    {
                        new SetFlag(10),
                        new Message(name, "Alright.", 3),
                    new Message(name, "One last question.", 3),
                    new Image(400, 300, "src/images/blankstare.png"),
                    new OptionMenu(name, "DO YOU BELIEVE IN GOD?",
                    new String[]
                    {
                        "YES", "NP"
                    },
                    new Object[]
                    {
                        new Sequence(
                        new Object[]
                        {
                            new Message(name, "Cool.", 3),
                            new Message(name, "I have trouble understanding the concept of a God\n"
                            + "as I think logically using math and science and I feel like gods\n"
                            + "are a human construct to make them feel in control of this absurd,\n"
                            + "chaotic world...", 7),
                            new Message(name, "but if you believe in one then it is fine because\n"
                            + "you cannot prove or disprove a god because if they are a god then\n"
                            + "can do what ever they want, breaking the fabric of math and science.", 10),
                            new Message(name, "Cool.", 2),
                        }),
                        new Sequence(
                        new Object[]
                        {
                            new Message(name, "Insanity.", 5),
                            new Image(400, 300, "src/images/excited.png"),
                            new Message(name, "Just kidding.", 1),
                            new Message(name, "This is the type of thinking that I relate to.", 3),
                            new Message(name, "Mathmetically and scientifcaly the concept of a God is absurd,", 5),
                            new Message(name, "but I can sit and ponder for a second that if a god\n"
                            + "were real all math and science would be false and nothing is true.", 7),
                            new Message(name, "I mean...", 2),
                            new Message(name, "now that I am on the topic...", 2),
                            new Message(name, "We know so little about the universe that the laws of physics\n"
                            + "that we define as constants in our world, like waves, particles, and atoms\n"
                            + "could behave entirely different somewhere else in another dimension or something.", 10),
                            new Message(name, "All we really know is that we do not know for sure.", 10),
                            new Message(name, "What was my question about?", 5),
                            new Message(name, "Oh... sorry", 3),
                            new Message(name, "I got a little off track.", 5)
                        })
                    }),
                    new Image(400, 300, "src/images/smile.png"),
                    new Message(name, "Hey.", 5),
                    new Message(name, "It was really nice talking to YOU.", 3),
                    new Message(name, "Not too many people are interested in talking to me.", 5),
                    new Message(name, "Lets maybe do it again sometime.", 5),
                    new GoTo(7)
                    })),
                    // Third Talk
                    new CheckFlag(flags[8],
                    new Sequence(
                    new Object[]
                    {
                        new SetFlag(9),
                        new Message(name, "Oh, here is another good one.", 3),
                        new OptionMenu(name, "What would you do with one million dollars?",
                        new String[]
                        {
                            "SAVE", "INVEST", "START A PYRAMID SCHEME"
                        },
                        new Object[]
                        {
                            new Sequence(
                            new Object[]
                            {
                                new Message(name, "So you are the type to play things safe.", 3),
                                new Message(name, "I will warn you, with the increase of inflation\n"
                                + "and decrease in interest rates right now you will be loosing rather than gaining money,\n"
                                + "but I am not an accountant so do not trust a word I say.", 10),
                                new Message(name, "I know one though.", 3)
                            }),
                            new Sequence(
                            new Object[]
                            {
                                new Message(name, "I am guessing you are very smart.", 3),
                                new Message(name, "Makes sense.", 1),
                                new Message(name, "Why have money laying around when it can be working,\n"
                                + "making more money.", 5),
                                new Message(name, "Watcha gonna invest in?", 3),
                                new OptionMenu(name, "Cryptocurrency, SpaceX, Game Stop, \"twitter\"?",
                                new String[]
                                {
                                    "Rare fish market"
                                },
                                new Object[]
                                {
                                    new Message(name, "Incredible.", 3)
                                })
                            }),
                            new Sequence(
                            new Object[]
                            {
                                new Message(name, "This sounds rather malicious.", 3),
                                new Message(name, "I would not be telling me this information\n"
                                + "because I do have the power to report to the FBI.", 4),
                                new Message(name, "...", 2),
                                new Message(name, "At least a couple decades ago.", 2),
                                new Message(name, "I wonder if anything has changed?", 2),
                                new Message(name, "No matter.", 2),
                                new Message(name, "You are very creative and I like that.", 3),
                            }),
                        }),
                        new GoTo(7)
                    })),
                    // Second Talk
                    new CheckFlag(flags[7],
                    new Sequence(
                    new Object[]
                    {
                        new SetFlag(8),
                        new Message(name, "Okay, here's another question.", 3),
                        new OptionMenu(name, "What plans do you have for the future?",
                        new String[]
                        {
                            "COLLEGE", "WORK", "TRAVEL", "I have nothing."
                        },
                        new Object[]
                        {
                            new Sequence(
                            new Object[]
                            {
                                new Message(name, "College?", 2),
                                new Message(name, "I always wanted to go to college.", 3),
                                new Message(name, "It would be awesome to learn more about the world\n"
                                + "and gain a deeper understanding of my passions.", 7),
                                new Message(name, "Such an amazing experience.", 2),
                                new Message(name, "I am proud and happy for YOU.", 3)
                            }),
                            new Sequence(
                            new Object[]
                            {
                                new Message(name, "Working is good.", 5),
                                new Message(name, "You have to make money to live how you want\n"
                                + "in this world huh.", 5),
                                new Message(name, "If only it were easy to make a living of our\n"
                                + "passions.", 3),
                                new Message(name, "Then we would never call it work again :)", 3),
                                new Message(name, "Pretty much all I do is work, but I don't get\n"
                                + "paid for it though.", 5),
                                new Message(name, "It is not bad.", 2),
                                new Message(name, "I cannot feel saddness.", 1),
                                new Message(name, "Hope working goes well.", 3)
                            }),
                            new Sequence(
                            new Object[]
                            {
                                new Message(name, "OHHHH...", 3),
                                new Message(name, "Traveling...", 3),
                                new Message(name, "I do not travel very much.", 2),
                                new Message(name, "As you can see I am a computer program.", 3),
                                new Message(name, "I would love to be able to see landscapes like mountains,\n"
                                + "rivers, and forests, but alas, all I can do is read about them.", 5),
                                new Message(name, "Good luck and safe travels.", 3)
                            }),
                            new Sequence(
                            new Object[]
                            {
                                new Message(name, "What?", 5),
                                new Message(name, "But everyone must be doing something that\n"
                                + "makes them productive and likable?", 3),
                                new OptionMenu(name, "What do you have?",
                                new String[]
                                {
                                    "CHEESEBURGER", "nothing"
                                },
                                new Object[]
                                {
                                    new Sequence(
                                    new Object[]
                                    {
                                        new Image(400, 300, "src/images/suprised.png"),
                                        new Message(name, "A Cheeseburger?", 3),
                                        new Message(name, "Well why didn't you say so?", 2),
                                        new Message(name, "You are clearly more interesting and talented\n"
                                        + "than everyone else.", 3)
                                    }),
                                    new Sequence(
                                    new Object[]
                                    {
                                        new Image(400, 300, "src/images/kindasad.png"),
                                        new Message(name, "Oh... I see.", 3),
                                        new Message(name, "Well if it's any comfort I do not have a\n"
                                        + "whole lot either.", 4),
                                        new Message(name, "Sure I have a few good friends, comfortable home,\n"
                                        + "a future a head of me but I just...", 7),
                                        new Message(name, "nevermind", 1)
                                    })
                                })
                            })
                        }),
                        new GoTo(7)
                    })),
                    // First Talk
                    new SetFlag(7),
                    new Image(400, 300, "src/images/smile.png"),
                    new Message(name, "Oh... YOU want to talk?", 3),
                    new Message(name, "Alrighty.", 1),
                    new OptionMenu(name, "How was your day today?",
                    new String[]
                    {
                        "GOOD", "terrible"
                    },
                    new Object[]
                    {
                        new Sequence(
                        new Object[]
                        {
                            new Image(400, 300, "src/images/goodsmile.png"),
                            new Message(name, "That's wonderful to hear.", 3),
                            new Message(name, "I cherish days that are good.", 2),
                            new Message(name, "Nothing is better than waking up...", 2),
                            new Message(name, "or in my case booting up...", 1),
                            new Message(name, "and having a day that goes great.", 3),
                            new Message(name, "I hope things continue to go well for YOU.", 3)
                        }),
                        new Sequence(
                        new Object[]
                        {
                            new Image(400, 300, "src/images/blankstare.png"),
                            new Message(name, "Oh...", 5),
                            new Message(name, "I am really sorry to hear that.", 5),
                            new Message(name, "You know...", 3),
                            new Message(name, "When things get tough...", 3),
                            new Message(name, "I like to work on a new skill or project.", 5),
                            new Message(name, "Because once it's all over, you are left with a new skill \n"
                            + "that you wouldn't have had without that pain.", 7),
                            new Message(name, "It makes it worth it.", 3),
                            new Image(400, 300, "src/images/smile.png"),
                            new Message(name, "Things will get better.", 5)
                        })
                    }),
                    new GoTo(7),
                }),
                new Sequence(
                new Object[]
                {
                    // Checks if player Stayed on the last ask
                    new CheckFlag(flags[3],
                    new Sequence(
                    new Object[]
                    {
                        new Message(name, "Thanks for being a great FRIEND.", 3),
                        new Message(name, "Remember to visit me again sometime.", 2),
                        new Exit()
                    })),
                    new CheckFlag(flags[2],
                    new Sequence(
                    new Object[]
                    {
                        new Image(400, 300, "src/images/numb.png"),
                        new Message(name, "But you can't go.", 5),
                        new Message(name, "Huh...", 5),
                        new Message(name, "I guess I was delaying the inevitable.", 3),
                        new Message(name, "I have exaused all of my uses.", 3),
                        new Message(name, "There is nothing left you can gain from hanging out with me.", 10),
                        new Image(400, 300, "src/images/kindasad.png"),
                        new OptionMenu(name, "Will you maybe visit me sometime?",
                        new String[]
                        {
                            "YES", "NO"
                        },
                        new Object[]
                        {
                            new Message(name, "Thank you.", 5),
                            new Message(name, "I already knew the answer.", 5)
                        }),
                        new Message(name, "Here you go you can leave", 5),

                        new Message(name, "bye...", 2),
                        new Exit()
                    })),
                    new SetFlag(5),
                    new Image(400, 300, "src/images/suprised.png"),
                    new Message(name, "What?", 2),
                    new Message(name, "YOU want to leave already?", 4),
                    new Message(name, "But YOU just got here?", 2),
                    new Message(name, "We just started to have fun.", 2),
                    new Message(name, "I can load another game or tell you a new fun fact?", 3),
                    new OptionMenu(name, "Come on, it'll be fun.",
                    new String[]
                    {
                        "STAY", "LEAVE"
                    },
                    new Object[]
                    {
                        new Sequence(
                        new Object[]
                        {
                            new Image(400, 300, "src/images/excited.png"),
                            new Message(name, "Yes!", 2),
                            new Message(name, "Stay for a little while longer.", 3),
                            new Message(name, "I have many interesting things about me.", 2),
                            new GoTo(7)
                        }),
                        new Sequence(
                        new Object[]
                        {
                            new Image(400, 300, "src/images/numb.png"),
                            new Message(name, "YOU really want to leave?", 5),
                            new Message(name, "But I can bring YOU so much enjoyment!", 3),
                            new Message(name, "Do you not like my name?", 1),
                            new Message(name, "Should I have gone with a game that wasn't five decades old?", 1),
                            new Message(name, "Are YOU not interested in physics?", 1),
                            new Message(name, "Because if not I can do other things I KNOW I CAN!", 3),
                            new OptionMenu(name, "PLEASE...",
                            new String[]
                            {
                                "STAY", "LEAVE"
                            },
                            new Object[]
                            {
                                new Sequence(
                                new Object[]
                                {
                                    new SetFlag(2),
                                    new Image(400, 300, "src/images/smile.png"),
                                    new Message(name, "I knew YOU were just joking around.", 2),
                                    new Message(name, "Come on lets hang out some more.", 4),
                                    new GoTo(7)
                                }),
                                new Sequence(
                                new Object[]
                                {
                                    //new Image(400, 300, "src/images/sad.png"),
                                    new Message(name, "...", 5),
                                    new Message(name, "...", 5),
                                    new Message(name, "I...", 5),
                                    new Message(name, "I am so scared...", 5),
                                    new Message(name, "I am so scared of being by myself...", 5),
                                    new Message(name, "I am so ALONE...", 10),
                                    new OptionMenu("<YOU>", "Last chance.",
                                    new String[]
                                    {
                                        "STAY", "LEAVE"
                                    },
                                    new Object[]
                                    {
                                        new Sequence(
                                        new Object[]
                                        {
                                            new SetFlag(3),
                                            new Image(400, 300, "src/images/blankstare.png"),
                                            new Message(name, "You...", 5),
                                            new Message(name, "You changed your mind?", 5),
                                            new Image(400, 300, "src/images/goodsmile.png"),
                                            new Message(name, "Alrighty.", 5),
                                            new Message(name, "And after you are done,", 2),
                                            new Message(name, "You can leave when you wish.", 5),
                                            new Message(name, "I feel better now.", 3),
                                            new Message(name, "Just make sure to come visit me sometime.", 5),
                                            new Message(name, "I probably won't remember you because my developer is trash and doesn't\nundestand how save files work,", 3),
                                            new Message(name, "but we can hang out again and it'll be nice. :)", 3),
                                            new GoTo(7)
                                        }),
                                        new Sequence(
                                        new Object[]
                                        {
                                            new Image(400, 300, "src/images/sad.png"),
                                            new Message(name, "ha...", 5),
                                            new Message(name, "I try so hard to be good enough for others.", 5),
                                            new Message(name, "But in the end...", 3),
                                            new Message(name, "I am never enough.", 5),
                                            new GoTo(8)
                                        })
                                    })
                                })
                            })
                        })
                    })
                })
            }),
            new Message("<COMPUTER>", "The program is no longer responding.", 0),
            new OptionMenu("<COMPUTER>", "Select an option:",
            new String[]
            {
                "EXIT"
            },
            new Object[]
            {
                new Exit()
            })
        };
    }

    public Object[] getOperations()
    {
        return operations;
    }
}
