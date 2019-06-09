package com.balki;

import com.balki.data.FileType;
import com.balki.util.FileOperations;
import com.balki.util.FileSplitter;

import java.io.IOException;

public class PreProcessMain {
    private final static int MIN_PATTERN_LENGTH = 7;
    private final static int MAX_PATTERN_LENGTH = 20;

    public static void main(String[] args) throws IOException {
        for(FileType type : FileType.values()){
            FileSplitter.splitFile("./texts/" + type.getFolderName() + "/","100-0.txt", type.getSplitCount(), false);
        }

        String[][] patternCandidates = {
                {"married do offen",
                "private widow well m",
                "fair a house fall to d",
                "private widow well m",
                "nature hath not made f",
                "fair a house fall to d",
                "and checked even",
                "war shall statues overturn,",
                "see again, and do not",
                "absence of your ",
                "rarities of nature’s tru",
                "that writes of you, if he ",
                "his spirit, by spirits t",
                "faults concealed, wherein I am a",
                "in the rearward of a con",
                "more delight than hawks and ho"},
                {"Kind is my love to-day, to-m", "In praise of ladies dead, and lov",
                        "Still constant in a won",
                        "Now with the drops of this most balm",
                        "That is my home of love, if I h",
                        "A god in love, to whom I am confined.",
                        "Whilst like a willing patient I",
                        "To make of monsters, and things ",
                        "But reckoning time, whose millio",
                        "It is the star to every wand’rin",
                        "To bitter sauces did I fram",
                        "For which the people stir;",
                        "I' th' plain way of his",
                        "Which we ourselves have plough'd",
                        "As for my country ",
                        "tors, have you thus"
                },
                {"All cause unborn, could never be th",
                        "The nature of our seats, an",
                        "Cannot conclude but by the yea",
                        "Of that integrity which sho",
                        "Out of thy garments.",
                        "The people are the city.",
                        "We have as many friends as enem",
                        "One time will owe anothe",
                        "Before the tag return? whose rage",
                        "Like interrupted waters, and ",
                        "And, being angry, does forget that ever",
                        "As I do know the consul's worth",
                        "Our certain death; therefore it is decree",
                        "For what before it was.",
                        "Why did you wish me milder? Would you h",
                        "Ere they lack'd power to cross you."
                }
        };
        StringBuffer sb = new StringBuffer();

        for(int i=0; i<3; i++) {
            for (int j = MIN_PATTERN_LENGTH; j <= MAX_PATTERN_LENGTH; j++) {
                sb.append(patternCandidates[i][j - MIN_PATTERN_LENGTH].substring(0, j)).append("\n");
            }
        }

        FileOperations.writeFile("./patterns/in_file.txt", sb.toString());

        String[] patternCandidates2 = {"abcdefghijklm123456789798", "d f f f  e e   d d f g e eeeee", "hfjdfg hfgjdhfgkjdf ljfgjdfgjdfg"};
        sb = new StringBuffer();

        for(int i=0; i<3; i++) {
            for (int j = MIN_PATTERN_LENGTH; j <= MAX_PATTERN_LENGTH; j++) {
                sb.append(patternCandidates2[i].substring(0, j)).append("\n");
            }
        }

        FileOperations.writeFile("./patterns/not_in_file.txt", sb.toString());
    }
}
