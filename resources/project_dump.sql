CREATE DATABASE IF NOT EXISTS `rpg_project` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `rpg_project`;
-- MySQL dump 10.13  Distrib 5.6.17, for Win32 (x86)
--
-- Host: localhost    Database: junia_lab03
-- ------------------------------------------------------
-- Server version	5.6.20

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `User`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `creationDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `name` varchar(30) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'2019-09-16 10:00:38', NULL, 'user1', 'password'),
                          (2,'2019-09-16 10:00:38', NULL, 'user2', 'password'),
                          (3,'2019-09-16 10:00:38', NULL, 'user3', 'password'),
                          (4,'2019-09-16 10:00:38', NULL, 'user4', 'password'),
                          (5,'2019-09-16 10:00:39', NULL, 'user5', 'password');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `party`
--

DROP TABLE IF EXISTS `party`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `party` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `creationDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `name` varchar(40) DEFAULT NULL,
  `sessionNumber` int(11) DEFAULT 0,
  `PC1` varchar(30) NOT NULL,
  `PC2` varchar(30) DEFAULT '',
  `PC3` varchar(30) DEFAULT '',
  `PC4` varchar(30) DEFAULT '',
  `notes` varchar(3000) DEFAULT NULL,
  `gmUser_id` bigint(20) NOT NULL,
  FOREIGN KEY (`gmUser_id`) REFERENCES `user` (`id`),
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `party`
--

LOCK TABLES `party` WRITE;
/*!40000 ALTER TABLE `party` DISABLE KEYS */;
INSERT INTO `party` VALUES (1,'2019-09-16 10:00:38', NULL, 'Les Fers de Hache', 2, 'user1', 'user4', 'user3', '', 'ceci sont des notes', 2);
/*!40000 ALTER TABLE `party` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `characterSheet`
--

DROP TABLE IF EXISTS `characterSheet`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `characterSheet` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `creationDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `name` varchar(40) NOT NULL DEFAULT '',
  `gender` varchar(1) NOT NULL DEFAULT 'U',
  `race` varchar(40) NOT NULL DEFAULT 'undefined',
  `will_stat` tinyint(3) NOT NULL DEFAULT 0,
  `strength_stat` tinyint(3) NOT NULL DEFAULT 0,
  `constitution_stat` tinyint(3) NOT NULL DEFAULT 0,
  `dexterity_stat` tinyint(3) NOT NULL DEFAULT 0,
  `intelligence_stat` tinyint(3) NOT NULL DEFAULT 0,
  `wisdom_stat` tinyint(3) NOT NULL DEFAULT 0,
  `charisma_stat` tinyint(3) NOT NULL DEFAULT 0,
  `level` tinyint(3) NOT NULL DEFAULT 0,
  `money` int(9) NOT NULL DEFAULT 0,
  `notes` varchar(3000) NOT NULL DEFAULT '',
  `user_id` bigint(20) NOT NULL,
  `party_id` bigint(20) DEFAULT NULL,
  FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  FOREIGN KEY (`party_id`) REFERENCES `party` (`id`),
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `characterSheet`
--

LOCK TABLES `characterSheet` WRITE;
/*!40000 ALTER TABLE `characterSheet` DISABLE KEYS */;
INSERT INTO `characterSheet`
    (id, creationDate, gender, race, name, level, user_id, party_id)
    VALUES (1, '2019-09-16 10:00:39', 'F', 'Human', 'Camille', 6, 2, null),
        (2, '2019-09-16 10:00:39', 'U', 'Human', 'Francis', 6, 4, 1),
        (3, '2019-09-16 10:00:39', 'M', 'Orc', 'Xorox', 6, 1, 1),
        (4, '2019-09-16 10:00:39', 'M', 'Dwarf', 'Philip', 7, 3, 1);
/*!40000 ALTER TABLE `characterSheet` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `spell`
--

DROP TABLE IF EXISTS `spell`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `spell` (
                         `id` bigint(20) NOT NULL AUTO_INCREMENT,
                         `creationDate` datetime DEFAULT NULL,
                         `updateDate` datetime DEFAULT NULL,
                         `name` varchar(255) DEFAULT NULL,
                         `level` smallint(3) DEFAULT 0,
                         `type` varchar(65) DEFAULT 'Undefined',
                         `description` varchar(1000) DEFAULT NULL,
                         PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `spell`
--

LOCK TABLES `spell` WRITE;
/*!40000 ALTER TABLE `spell` DISABLE KEYS */;
INSERT INTO `spell` (name, level, type, description)
VALUES ('Resistance', 0, 'Abjuration', 'Subject gains +1 on saving throws.'),
       ('Acid Splash', 0, 'Conjuration', 'Orb deals 1d3 acid damage.'),
       ('Drench', 0, 'Conjuration', 'A sudden downpour soaks a target creature or object.'),
       ('Detect Fiendish Presence', 0, 'Divination', 'This spell functions like detect evil, except that it detects only outsiders with the evil subtype, as well as the lingering effects caused by their gifts, presence, and spells. It can also detect clerics and paladins of fiendish deities, including Asmodeus, archdevils, daemonic harbingers, and demon lords.'),
       ('Detect Magic', 0, 'Divination', 'Detects all spells and magic items within 60 ft.'),
       ('Detect Poison', 0, 'Divination', 'Detects poison in one creature or small object.'),
       ('Daze', 0, 'Enchantment', 'A single humanoid creature with 4 HD or less loses its next action.'),
       ('Breeze', 0, 'Evocation', 'Create a light wind that blows against target from direction of your choice.'),
       ('Dancing Lights', 0, 'Evocation', 'Creates torches or other lights.'),
       ('Flare', 0, 'Evocation', 'Dazzles one creature (–1 on attack rolls).'),
       ('Ray of Frost', 0, 'Evocation', 'Ray deals 1d3 cold damage.'),
       ('Spark', 0, 'Evocation', 'Ignites flammable objects.'),
       ('Haunted Fey Aspect', 0, 'Illusion', ' 	You surround yourself with disturbing illusions.'),
       ('Disrupt Undead', 0, 'Necromancy', 'Deals 1d6 damage to one undead.'),
       ('Jolt', 0, 'Transmutation', 'Deal 1d3 electrical damage with a ranged touch attack.'),
       ('Mage Hand', 0, 'Transmutation', '5-pound telekinesis.'),
       ('Message', 0, 'Transmutation', 'Whisper conversation at distance.'),
       ('Prestidigitation', 0, 'Universal', 'Performs minor tricks.'),

       ('Alarm', 1, 'Abjuration', 'Wards an area for 2 hours/level.'),
       ('Incendiary Runes', 1, 'Abjuration', 'As explosive runes, except it creates a small surge of flames rather than an explosion of force. The runes automatically deal 1d6 points of fire damage to any creatures and objects in adjacent squares, and those creatures and objects catch fire unless they succeed at Reflex saves.'),
       ('Shield', 1, 'Abjuration', 'Invisible disc gives +4 to AC, blocks magic missiles.'),
       ('Obscure Poison', 1, 'Abjuration', 'Make it harder to detect a poison or a venomous creature.'),
       ('Expeditious Construction', 1, 'Conjuration', 'You create a low wall or other simple structure of packed earth or loose stone measuring 3 feet thick, 3 feet tall, and 10 feet long per 3 caster levels you possess (minimum 10 feet).'),
       ('Corrosive Touch', 1, 'Conjuration', 'Touch attack deals 1d4 acid/level.'),
       ('Summon Minor Monster', 1, 'Conjuration', 'Summon 1d3 Tiny animals.'),
       ('Snowball', 1, 'Conjuration', 'Ranged touch delivers 1d6/level cold damage (max 5d6) and might stagger a foe.'),
       ('Comprehend Languages', 1, 'Divination', 'You understand all spoken and written languages.'),
       ('Detect Metal', 1, 'Divination', 'You detect any metal objects or creatures within a 60-foot cone.'),
       ('Discern Next of Kin', 1, 'Divination', 'Read the target’s mind to learn about its family.'),
       ('Identify', 1, 'Divination', 'Gives +10 bonus to identify magic items.'),
       ('Hypnotism', 1, 'Enchantment', 'Fascinates 2d4 HD of creatures.'),
       ('Memory Lapse', 1, 'Enchantment', 'Subject forgets events back to last turn.'),
       ('Sleep', 1, 'Enchantment', 'Puts 4 HD of creatures into magical slumber.'),
       ('Charm Person', 1, 'Enchantment', 'Makes one person your friend.'),
       ('Burning Hands', 1, 'Evocation', '1d4/level fire damage (max 5d4).'),
       ('Magic Missile', 1, 'Evocation', '1d4+1 damage; +1 missile per two levels above 1st (max 5).'),
       ('Thunderstomp', 1, 'Evocation', 'Trip one creature within range.'),
       ('Touch of Combustion', 1, 'Evocation', 'Touched target ignites in flame, suffering 1d6 fire damage and possibly catching on fire.'),
       ('Clarion Call', 1, 'Illusion', 'Your voice can be heard across great distances.'),
       ('Negative Reaction', 1, 'Illusion', 'Targeted creature may not positively influence anyone.'),
       ('Darting Duplicate', 1, 'Illusion', 'You create an illusory duplicate of yourself that opponents might waste an attack of opportunity on.'),
       ('Blend', 1, 'Illusion', 'Gain a bonus to Stealth and make checks without cover or concealment. Elf only.'),
       ('Decompose Corpse', 1, 'Necromancy', 'Turn a corpse into a clean skeleton.'),
       ('Touch of Blindness', 1, 'Necromancy', 'A touch from your hand, which is engulfed in darkness, disrupts a creature’s vision by coating its eyes in supernatural darkness.'),
       ('Ray of Sickening', 1, 'Necromancy', 'Ray makes the subject sickened.'),
       ('Ray of Enfeeblement', 1, 'Necromancy', 'Ray causes 1d6 Str penalty + 1 per 2 levels.'),
       ('Animate Rope', 1, 'Transmutation', 'Makes a rope move at your command.'),
       ('Burning Disarm', 1, 'Transmutation', 'A metal object instantly becomes red hot possibly causing the wielder to drop it or take damage.'),
       ('Long Arm', 1, 'Transmutation', 'Your arms lengthen, giving you extra reach.'),
       ('Monkey Fish', 1, 'Transmutation', 'Gain a climb speed and a swim speed of 10 ft. for a time.'),

       ('Book Ward', 2, 'Abjuration', 'As protection from energy, except lasts much longer and only protects against acid and fire damage.'),
       ('Endure Elements, Communal', 2, 'Abjuration', 'As endure elements, but you may divide the duration among creatures touched.'),
       ('Protection from Law, Communal', 2, 'Abjuration', 'As protection from law, but you may divide the duration among creatures touched.'),
       ('With the Wind', 2, 'Abjuration', 'Protect a target from being blown away by wind of less than windstorm force.'),
       ('Arrow Eruption', 2, 'Conjuration', 'Creates duplicates of killing arrow.'),
       ('Fog Cloud', 2, 'Conjuration', 'Fog obscures vision.'),
       ('Retrieve Item', 2, 'Conjuration', 'Call an item instantly to you hand from nearby by speaking a special word and snapping your fingers. The spell fails if the item is in the possession of another creature.'),
       ('Web', 2, 'Conjuration', 'Fills 20-ft.-radius spread with sticky spiderwebs that can grapple foes and impair movement.'),
       ('Blood Transcription', 2, 'Divination', 'Wizard Only. Learn a spell from the target’s blood.'),
       ('Elemental Speech', 2, 'Divination', 'Enables you to speak to elementals and some creatures.'),
       ('Track Ship', 2, 'Divination', 'Track a ships location and movement with the aid of a nautical chart and a piece of the ship to be tracked.'),
       ('Share Memory', 2, 'Divination', 'Share one memory with the target.'),
       ('Bestow Weapon Proficiency', 2, 'Enchantment', 'Grants a creature proficiency in a single weapon for short period of time.'),
       ('Compulsive Liar', 2, 'Enchantment', 'Prevent target from speaking the truth.'),
       ('Hidden Presence', 2, 'Enchantment', 'Prevent creatures from noticing your presence.'),
       ('Touch of Idiocy', 2, 'Enchantment', 'Subject takes 1d6 penalty to Int, Wis, and Cha.'),
       ('Aggressive Thundercloud', 2, 'Evocation', 'Flying storm cloud deals 3d6 electricity damage.'),
       ('Contact Entity I', 2, 'Evocation', 'Ask eldritch entities to find and converse with you.'),
       ('Force Sword', 2, 'Evocation', 'You create a longsword of pure force sized appropriately for you that you can wield or give to another creature.'),
       ('Shatter', 2, 'Evocation', 'Sonic energy damages objects or crystalline creatures.'),
       ('Ghostly Disguise', 2, 'Illusion', 'You look like a ghost of yourself.'),
       ('Jitterbugs', 2, 'Illusion', 'Targets gets –4 penalty on all Dex and Dex-based skill checks, and cannot take the delay, ready, or total defense actions.'),
       ('Shifted Steps', 2, 'Illusion', 'Make a target sound as if elsewhere.'),
       ('Twilight Haze', 2, 'Illusion', 'Illusory fog obscures vision.'),
       ('Bone Fists', 2, 'Necromancy', 'The bones of your targets’ joints grow thick and sharp, protruding painfully through the skin at the knuckles, elbows, shoulders, spine, and knees.'),
       ('Defoliate', 2, 'Necromancy', 'You hurl a tiny ball of negative energy, destroying plant life either in a line 60 feet long or a 10-foot-radius spread.'),
       ('Life Pact', 2, 'Necromancy', 'Affected creatures automatically donate hp to stabilize fallen ally.'),
       ('Spectral Hand', 2, 'Necromancy', 'Creates disembodied glowing hand to deliver touch attacks.'),
       ('Adhesive Blood', 2, 'Transmutation', 'Attackers’ weapons stick to your gluey blood.'),
       ('Angelic Aspect, Lesser', 2, 'Transmutation', 'You gain low-light vision, acid and cold resistance 5, and protection from evil.'),
       ('Boiling Blood', 2, 'Transmutation', 'Targets take fire damage; orcs get +2 Strength.'),
       ('Recoil Fire', 2, 'Transmutation', 'Ammunition in the targeted firearm generates excessive recoil.'),

       ('Explosive Runes', 3, 'Abjuration', 'Deals 6d6 damage when read.'),
       ('Protection from Arrows, Communal', 3, 'Abjuration', 'As protection from arrows, but you may divide the duration among creatures touched.'),
       ('Selective Alarm', 3, 'Abjuration', 'As alarm, but only against selected creatures.'),
       ('Shield Companion', 3, 'Abjuration', 'As shield other, but affecting your companion creature.'),
       ('Ash Storm', 3, 'Conjuration', 'Hamper vision and movement.'),
       ('Iron Stake', 3, 'Conjuration', 'Hurl a spike of cold iron at a foe.'),
       ('Phantom Steed', 3, 'Conjuration', 'Magic horse appears for 1 hour/level.'),
       ('Stinking Cloud', 3, 'Conjuration', 'Nauseating vapors, 1 round/level.'),
       ('Aura Sight', 3, 'Divination', 'Alignment auras become visible to you.'),
       ('Detect Desires', 3, 'Divination', 'Learn what creatures desire.'),
       ('Locate Weakness', 3, 'Divination', 'You roll damage twice when you roll damage for a critical hit and take the best damage.'),
       ('Tongues', 3, 'Divination', 'Speak and understand any language.'),
       ('Deflect Blame', 3, 'Enchantment', 'Blame someone else for your action.'),
       ('Matchmaker', 3, 'Enchantment', 'Cause two creatures to fall in love.'),
       ('Open Book', 3, 'Enchantment', 'Make it permanently easier to learn more about a target.'),
       ('Reckless Infatuation', 3, 'Enchantment', 'Target is compelled to stay near another.'),
       ('Battering Blast', 3, 'Evocation', 'You hurl a fist-sized ball of force resembling a sphere of spikes to ram a designated creature or object.'),
       ('Contingent Action', 3, 'Evocation', 'Set the condition for triggering a target’s readied standard, move, or swift action.'),
       ('Fireball', 3, 'Evocation', '1d6 damage per level, 20-ft. radius.'),
       ('Pain Strike', 3, 'Evocation', 'Inflicts 1d6 nonlethal damage 1 round/level.'),
       ('Adjustable Disguise', 3, 'Illusion', 'As disguise self, but you can change the disguise as a swift action.'),
       ('Minor Dream', 3, 'Illusion', 'As dream but messenger is you or a gnome, and the message cannot be longer than 20 words.'),
       ('Shadow Enchantment', 3, 'Illusion', 'You use material from the Shadow Plane to cast a quasi-real, illusory version of a psychic, sorcerer, or wizard enchantment spell of 2nd level or lower.'),
       ('Eldritch Fever', 3, 'Illusion', 'Target gains the eldritch ague spellblight.'),
       ('Aura of Cannibalism', 3, 'Necromancy', 'You emanate an aura that saps the strength of others of your kind and channels their energy into you.'),
       ('Ki Leech', 3, 'Necromancy', 'Add to your ki pool when you critically hit.'),
       ('Unliving Rage', 3, 'Necromancy', 'As rage, except affecting only undead.'),
       ('Vampiric Touch', 3, 'Necromancy', 'Touch deals 1d6 damage per two levels; caster gains damage as temporary hp.'),
       ('Beast Shape I', 3, 'Transmutation', 'You take the form and some of the powers of a Small or Medium animal.'),
       ('Countless Eyes', 3, 'Transmutation', 'Extra eyes give all-around vision.'),
       ('Devolution', 3, 'Transmutation', 'Target eidolon temporarily loses 1 evolution +1/five levels.'),
       ('Flame Arrow', 3, 'Transmutation', 'Arrows deal +1d6 fire damage.');
    /*!40000 ALTER TABLE `spell` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=INNODB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (35),(35),(35),(35);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;
