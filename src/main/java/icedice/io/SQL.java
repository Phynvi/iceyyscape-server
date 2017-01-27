package icedice.io;

import java.sql.*;

import icedice.players.Player;

public class SQL {

    public static Connection con = null;
    public static Statement stmt;

    public static void createConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            String IP = "localhost";
            String DB = "highscores";
            String User = "root";
            String Pass = "e]*))7Q7@)f~eS<^|<O7Y";
            con = DriverManager.getConnection("jdbc:mysql://" + IP + "/" + DB, User, Pass);
            stmt = con.createStatement();
        } catch (Exception e) {
        }
    }

    public static ResultSet query(String s) throws SQLException {
        try {
            if (s.toLowerCase().startsWith("select")) {
                ResultSet rs = stmt.executeQuery(s);
                return rs;
            } else {
                stmt.executeUpdate(s);
            }
            return null;
        } catch (Exception e) {
            destroyConnection();
            createConnection();
            //
        }
        return null;
    }

    public static void destroyConnection() {
        try {
            stmt.close();
            con.close();
        } catch (Exception e) {
            //
        }
    }

    public static boolean saveHighScore(Player p) {
        try {
            if (!p.username.equals("gibson306")) {
                query("DELETE FROM `skills` WHERE playerName = '" + p.username + "';");
                query("DELETE FROM `skillsoverall` WHERE playerName = '" + p.username + "';");
                query("INSERT INTO `skills` (`playerName`,`Attacklvl`,`Attackxp`,`Defencelvl`,`Defencexp`,`Strengthlvl`,`Strengthxp`,`Hitpointslvl`,`Hitpointsxp`,`Rangelvl`,`Rangexp`,`Prayerlvl`,`Prayerxp`,`Magiclvl`,`Magicxp`,`Cookinglvl`,`Cookingxp`,`Woodcuttinglvl`,`Woodcuttingxp`,`Fletchinglvl`,`Fletchingxp`,`Fishinglvl`,`Fishingxp`,`Firemakinglvl`,`Firemakingxp`,`Craftinglvl`,`Craftingxp`,`Smithinglvl`,`Smithingxp`,`Mininglvl`,`Miningxp`,`Herblorelvl`,`Herblorexp`,`Agilitylvl`,`Agilityxp`,`Thievinglvl`,`Thievingxp`,`Slayerlvl`,`Slayerxp`,`Farminglvl`,`Farmingxp`,`Runecraftlvl`,`Runecraftxp`,`Hunterlvl`,`Hunterxp`,`Constructionlvl`,`Constructionxp`,`Summoninglvl`,`Summoningxp`) VALUES ('" + p.username + "'," + p.getLevelForXP(0) + "," + p.skillXP[0] + "," + p.getLevelForXP(1) + "," + p.skillXP[1] + "," + p.getLevelForXP(2) + "," + p.skillXP[2] + "," + p.getLevelForXP(3) + "," + p.skillXP[3] + "," + p.getLevelForXP(4) + "," + p.skillXP[4] + "," + p.getLevelForXP(5) + "," + p.skillXP[5] + "," + p.getLevelForXP(6) + "," + p.skillXP[6] + "," + p.getLevelForXP(7) + "," + p.skillXP[7] + "," + p.getLevelForXP(8) + "," + p.skillXP[8] + "," + p.getLevelForXP(9) + "," + p.skillXP[9] + "," + p.getLevelForXP(10) + "," + p.skillXP[10] + "," + p.getLevelForXP(11) + "," + p.skillXP[11] + "," + p.getLevelForXP(12) + "," + p.skillXP[12] + "," + p.getLevelForXP(13) + "," + p.skillXP[13] + "," + p.getLevelForXP(14) + "," + p.skillXP[14] + "," + p.getLevelForXP(15) + "," + p.skillXP[15] + "," + p.getLevelForXP(16) + "," + p.skillXP[16] + "," + p.getLevelForXP(17) + "," + p.skillXP[17] + "," + p.getLevelForXP(18) + "," + p.skillXP[18] + "," + p.getLevelForXP(19) + "," + p.skillXP[19] + "," + p.getLevelForXP(20) + "," + p.skillXP[20] + "," + p.getLevelForXP(21) + "," + p.skillXP[21] + "," + p.getLevelForXP(22) + "," + p.skillXP[22] + "," + p.getLevelForXP(23) + "," + p.skillXP[23] + ");");
                query("INSERT INTO `skillsoverall` (`playerName`,`lvl`,`xp`) VALUES ('" + p.username + "'," + (p.getLevelForXP(0) + p.getLevelForXP(1) + p.getLevelForXP(2) + p.getLevelForXP(3) + p.getLevelForXP(4) + p.getLevelForXP(5) + p.getLevelForXP(6) + p.getLevelForXP(7) + p.getLevelForXP(8) + p.getLevelForXP(9) + p.getLevelForXP(10) + p.getLevelForXP(11) + p.getLevelForXP(12) + p.getLevelForXP(13) + p.getLevelForXP(14) + p.getLevelForXP(15) + p.getLevelForXP(16) + p.getLevelForXP(17) + p.getLevelForXP(18) + p.getLevelForXP(19) + p.getLevelForXP(20) + p.getLevelForXP(21) + p.getLevelForXP(22) + p.getLevelForXP(23)) + "," + ((p.skillXP[0]) + (p.skillXP[1]) + (p.skillXP[2]) + (p.skillXP[3]) + (p.skillXP[4]) + (p.skillXP[5]) + (p.skillXP[6]) + (p.skillXP[7]) + (p.skillXP[8]) + (p.skillXP[9]) + (p.skillXP[10]) + (p.skillXP[11]) + (p.skillXP[12]) + (p.skillXP[13]) + (p.skillXP[14]) + (p.skillXP[15]) + (p.skillXP[16]) + (p.skillXP[17]) + (p.skillXP[18]) + (p.skillXP[19]) + (p.skillXP[20]) + (p.skillXP[21]) + (p.skillXP[22]) + (p.skillXP[23])) + ");");
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
