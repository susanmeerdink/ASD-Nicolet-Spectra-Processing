import java.lang.reflect.Array;

public class Spectra {
	
	private double[] rawValues; //This will hold raw ASD spectral values
	private String sampleID; //This will hold sample ID or name
	//This holds the correction values for the Nicolet sensor (source Elsa Abbott - JPL)
	private double[] corrValues = {0.961228,0.960947,0.96123,0.961394,0.961607,0.961883,0.961897,0.961606,0.961659,0.961966,0.961648,0.961689,0.962128,0.962096,0.961944,0.9622,0.962099,0.96191,0.96167,0.962122,0.962227,0.961738,0.961362,0.961939,0.962221,0.962648,0.963593,0.963808,0.963123,0.963613,0.964168,0.962778,0.962021,0.961963,0.962761,0.964587,0.964597,0.963352,0.96319,0.964289,0.963526,0.963976,0.965365,0.965859,0.963733,0.961246,0.961081,0.961287,0.963005,0.968037,0.9716,0.971564,0.97074,0.96668,0.963136,0.964755,0.968241,0.966934,0.964976,0.970241,0.968861,0.966848,0.969211,0.965919,0.963423,0.9667,0.967598,0.971804,0.971984,0.968086,0.970379,0.969045,0.96626,0.964244,0.96671,0.976448,0.98328,0.976393,0.966741,0.963094,0.96422,0.966809,0.968528,0.971619,0.974906,0.972353,0.968714,0.96791,0.966202,0.964967,0.966313,0.965823,0.971393,0.974229,0.969849,0.972625,0.971253,0.963797,0.96265,0.965893,0.971366,0.968943,0.970344,0.974598,0.969436,0.966793,0.9667,0.963317,0.96205,0.961764,0.962448,0.963157,0.96361,0.963547,0.966472,0.965803,0.963094,0.961962,0.963476,0.967482,0.96733,0.965923,0.966145,0.963798,0.966569,0.96778,0.967091,0.966944,0.973694,0.978487,0.977755,0.973659,0.97902,0.978893,0.968847,0.966596,0.971207,0.975653,0.973982,0.971507,0.966107,0.965078,0.967325,0.967183,0.966979,0.965825,0.964482,0.963424,0.965466,0.971358,0.97232,0.968194,0.963281,0.961913,0.964966,0.965815,0.962591,0.961972,0.962285,0.964265,0.969326,0.973652,0.971959,0.964222,0.961866,0.962314,0.964718,0.968816,0.976246,0.973436,0.970024,0.973605,0.969743,0.963216,0.96129,0.961401,0.961232,0.963369,0.967952,0.966065,0.96557,0.973142,0.975988,0.972096,0.965283,0.962159,0.961788,0.961701,0.961605,0.96193,0.963416,0.966376,0.97425,0.972768,0.964217,0.961312,0.963696,0.968503,0.968558,0.965539,0.965914,0.966837,0.967101,0.967991,0.964727,0.961688,0.961897,0.963093,0.962438,0.962647,0.963672,0.962906,0.962258,0.965835,0.968121,0.964885,0.961588,0.961012,0.961411,0.961418,0.96114,0.960919,0.96101,0.96342,0.967191,0.966597,0.963414,0.961946,0.961138,0.960656,0.960641,0.961281,0.961727,0.960943,0.96108,0.96257,0.962249,0.96093,0.959691,0.959296,0.959978,0.960298,0.959781,0.960429,0.961472,0.961535,0.961224,0.961117,0.960372,0.959407,0.959018,0.959066,0.959381,0.95984,0.959962,0.959813,0.959504,0.959987,0.960363,0.959492,0.959168,0.959585,0.959513,0.959165,0.958802,0.958488,0.958367,0.958534,0.959302,0.959527,0.958784,0.958322,0.958503,0.958828,0.958898,0.959048,0.959101,0.958592,0.958745,0.95866,0.958289,0.958196,0.95856,0.958381,0.95789,0.957977,0.958445,0.958536,0.958247,0.958464,0.958467,0.957645,0.957045,0.957301,0.957894,0.957899,0.957254,0.956991,0.957306,0.957735,0.958138,0.958081,0.957495,0.957646,0.957818,0.957829,0.957546,0.957024,0.956651,0.956936,0.957187,0.957241,0.957201,0.957189,0.957035,0.956799,0.956913,0.957313,0.957531,0.957533,0.957313,0.957337,0.957212,0.956996,0.956867,0.956985,0.957223,0.957185,0.956962,0.956867,0.956781,0.956903,0.956942,0.956969,0.957391,0.957505,0.957408,0.957437,0.957264,0.957184,0.957182,0.956984,0.956498,0.956535,0.957351,0.957369,0.956994,0.956923,0.956908,0.95745,0.957713,0.95733,0.956777,0.956907,0.957257,0.957551,0.957471,0.957338,0.957513,0.957421,0.957072,0.956953,0.957299,0.957522,0.957431,0.957695,0.957682,0.957587,0.957391,0.95702,0.956616,0.956812,0.957239,0.95727,0.957291,0.957417,0.957786,0.95811,0.957977,0.957512,0.957486,0.957772,0.95769,0.957445,0.957483,0.957666,0.957824,0.957915,0.957809,0.95778,0.957806,0.957942,0.957727,0.957547,0.958107,0.958029,0.95782,0.957915,0.957805,0.958182,0.95857,0.958213,0.957963,0.957944,0.957958,0.958216,0.958246,0.958222,0.958557,0.958642,0.958356,0.958219,0.958326,0.958499,0.958464,0.958273,0.958554,0.958667,0.958816,0.958562,0.958373,0.958407,0.958293,0.958236,0.958093,0.957977,0.958296,0.958506,0.958586,0.958706,0.958581,0.958392,0.95846,0.958524,0.958705,0.958587,0.958276,0.958308,0.958333,0.958416,0.958578,0.958544,0.958416,0.958372,0.958623,0.958948,0.959084,0.958832,0.958958,0.95928,0.959036,0.958528,0.958713,0.958653,0.958495,0.958667,0.95888,0.958917,0.959236,0.95938,0.959176,0.959012,0.959001,0.958867,0.958889,0.959226,0.95963,0.959511,0.95925,0.959151,0.959076,0.959157,0.959072,0.959101,0.959018,0.958457,0.958601,0.959101,0.959037,0.958882,0.959042,0.959015,0.958878,0.959158,0.95928,0.959205,0.958927,0.958884,0.95911,0.959195,0.959163,0.958985,0.959112,0.959064,0.958907,0.959122,0.95921,0.959241,0.959078,0.958688,0.958806,0.959286,0.959631,0.959589,0.959297,0.959182,0.959088,0.959057,0.959327,0.959303,0.959149,0.959187,0.959375,0.959535,0.959463,0.959322,0.959284,0.959109,0.959049,0.959172,0.959244,0.959324,0.959077,0.958697,0.958975,0.95918,0.958844,0.958717,0.958719,0.958791,0.958754,0.958448,0.958385,0.958232,0.958171,0.958215,0.958419,0.958417,0.958202,0.957892,0.957449,0.957123,0.95742,0.957684,0.957329,0.957175,0.957311,0.957419,0.957403,0.957241,0.957092,0.957175,0.957199,0.956902,0.956527,0.956457,0.956483,0.956298,0.956234,0.956436,0.956645,0.956678,0.956939,0.957279,0.957433,0.957728,0.957899,0.957852,0.957793,0.957684,0.957787,0.957814,0.958141,0.958611,0.9587,0.958506,0.958542,0.958272,0.958168,0.958463,0.958673,0.958719,0.95895,0.958923,0.958736,0.958694,0.958654,0.958592,0.958782,0.958874,0.958342,0.958058,0.958247,0.958243,0.95812,0.958057,0.958374,0.958503,0.958668,0.958863,0.958997,0.959381,0.95971,0.959797,0.959782,0.959948,0.960286,0.960312,0.960156,0.959919,0.959886,0.960113,0.960244,0.960042,0.959916,0.959846,0.960094,0.960312,0.96013,0.95993,0.959889,0.959992,0.960366,0.960506,0.96001,0.959933,0.960273,0.960577,0.960629,0.960573,0.960753,0.960632,0.960298,0.960038,0.959921,0.960243,0.960345,0.960453,0.960552,0.960274,0.960268,0.9604,0.960514,0.960347,0.960178,0.960381,0.96059,0.960612,0.960589,0.960571,0.960498,0.960571,0.960641,0.96077,0.960718,0.960623,0.960542,0.960563,0.960707,0.960644,0.960606,0.960885,0.960755,0.960616,0.960793,0.960837,0.960848,0.960695,0.960677,0.960942,0.960987,0.960798,0.960825,0.960864,0.960767,0.960818,0.960958,0.961168,0.961329,0.961238,0.961113,0.960993,0.960925,0.961036,0.960936,0.960856,0.96102,0.961046,0.960962,0.96102,0.961043,0.960949,0.960995,0.960871,0.960964,0.961114,0.961212,0.961162,0.961092,0.961115,0.961204,0.961199,0.961301,0.961336,0.961225,0.961469,0.961507,0.961285,0.961159,0.961234,0.961383,0.961313,0.961243,0.961197,0.961151,0.961219,0.961233,0.961102,0.961175,0.9612,0.961079,0.9613,0.961404,0.961303,0.961361,0.961443,0.961417,0.961381,0.961325,0.961369,0.961382,0.961127,0.961272,0.961341,0.961375,0.96148,0.961399,0.961217,0.961292,0.961617,0.961544,0.961397,0.961431,0.961455,0.96142,0.961469,0.961639,0.961671,0.96168,0.96162,0.961553,0.961763,0.961768,0.961734,0.961658,0.961511,0.961421,0.961486,0.961694,0.961871,0.961815,0.961769,0.96177,0.961569,0.961385,0.961559,0.961698,0.961627,0.961559,0.961492,0.961363,0.961434,0.96156,0.961456,0.961513,0.961838,0.961931,0.961708,0.961666,0.961676,0.961771,0.961732,0.96171,0.96193,0.962061,0.961861,0.961603,0.961727,0.961892,0.961882,0.962023,0.962128,0.962051,0.961898,0.961933,0.961988,0.961955,0.962005,0.962007,0.961874,0.961962,0.961997,0.962008,0.962015,0.961965,0.962071,0.962149,0.961871,0.961773,0.961884,0.962021,0.961962,0.962007,0.962029,0.962006,0.962229,0.962259,0.961955,0.962014,0.962303,0.96225,0.962172,0.962143,0.962081,0.962119,0.962075,0.961968,0.961892,0.962188,0.962334,0.962425,0.962294,0.962026,0.962048,0.962293,0.962145,0.962235,0.962284,0.962179,0.962388,0.962585,0.962608,0.962354,0.962112,0.962032,0.961769,0.961604,0.961297,0.961512,0.961325,0.960706,0.960882,0.961361,0.961441,0.961654,0.962472,0.963001,0.962689,0.961706,0.961608,0.961981,0.962362,0.962316,0.962098,0.962738,0.963183,0.962954,0.962771,0.962701,0.962603,0.962551,0.962606,0.962584,0.962595,0.962602,0.962571,0.962999,0.963141,0.963099,0.963144,0.963334,0.963237,0.962838,0.962681,0.962821,0.962967,0.963118,0.962938,0.962892,0.962884,0.96297,0.963129,0.963099,0.962955,0.962953,0.963037,0.962928,0.96288,0.962864,0.96278,0.962989,0.963249,0.963238,0.963199,0.963119,0.963004,0.962816,0.962765,0.96303,0.963022,0.962928,0.962907,0.962737,0.962698,0.962845,0.96286,0.9629,0.962745,0.962676,0.962828,0.962962,0.962911,0.962712,0.962549,0.962661,0.962861,0.962831,0.962794,0.962933,0.962951,0.962911,0.962794,0.962721,0.962857,0.962833,0.962871,0.962927,0.962952,0.962992,0.963092,0.963222,0.96318,0.963023,0.962995,0.962915,0.962962,0.962982,0.962843,0.962737,0.962762,0.963078,0.963171,0.963013,0.962987,0.963153,0.963105,0.962963,0.96292,0.963038,0.963201,0.963128,0.962981,0.962918,0.963125,0.963295,0.963335,0.963361,0.963248,0.963203,0.963223,0.963169,0.963279,0.963371,0.963311,0.963176,0.963141,0.963229,0.963213,0.963177,0.963091,0.963221,0.963315,0.963215,0.962992,0.963144,0.963227,0.963336,0.963343,0.963287,0.963425,0.963519,0.963368,0.963312,0.963312,0.963272,0.963158,0.96323,0.963423,0.963606,0.963623,0.963515,0.963419,0.963516,0.963721,0.963557,0.963455,0.963395,0.963328,0.963421,0.963422,0.963345,0.963279,0.963395,0.963496,0.963534,0.963566,0.963602,0.963506,0.963474,0.963392,0.963263,0.963296,0.963357,0.963353,0.963417,0.96342,0.963522,0.963698,0.9639,0.963611,0.963387,0.963545,0.963618,0.963599,0.96361,0.963656,0.963502,0.963498,0.963576,0.963832,0.964254,0.964992,0.964553,0.96408,0.963833,0.963479,0.963502,0.963485,0.963339,0.963319,0.963503,0.963518,0.963483,0.964062,0.964857,0.96439,0.963658,0.963459,0.963428,0.963558,0.963784,0.963669,0.963544,0.963695,0.963857,0.964165,0.965387,0.966077,0.964576,0.963758,0.963553,0.963623,0.963742,0.963642,0.963593,0.96375,0.964301,0.965385,0.964925,0.966342,0.966596,0.964397,0.96386,0.964417,0.964967,0.964409,0.963884,0.963753,0.963737,0.963604,0.963782,0.964224,0.964219,0.964357,0.965881,0.965217,0.964043,0.963942,0.963605,0.963548,0.963685,0.963776,0.963628,0.963896,0.966673,0.969361,0.967347,0.964768,0.964009,0.963996,0.964041,0.964147,0.963794,0.963791,0.963686,0.96429,0.965255,0.968897,0.971032,0.966479,0.963882,0.964218,0.964295,0.96388,0.965692,0.969285,0.968132,0.967052,0.967541,0.965076,0.964194,0.964045,0.964325,0.963733,0.96492,0.966265,0.965615,0.964297,0.964234,0.965249,0.967332,0.967208,0.965832,0.966158,0.97017,0.971369,0.966842,0.964139,0.9656,0.965972,0.965747,0.966338,0.965087,0.965603,0.969843,0.974372,0.971319,0.968816,0.966504,0.964331,0.967964,0.968253,0.96594,0.966953,0.965193,0.966882,0.971694,0.97067,0.967934,0.965844,0.966072,0.967084,0.968847,0.965863,0.971528,0.977352,0.970518,0.965236,0.962799,0.961249,0.961971,0.962047,0.965905,0.974603,0.975265,0.969576,0.964392,0.961862,0.963104,0.966431,0.968121,0.968314,0.978219,0.977416,0.974081,0.974383,0.964496,0.961505,0.962858,0.964456,0.974111,0.977524,0.967451,0.962323,0.962108,0.968362,0.967239,0.964643,0.968918,0.967046,0.960014,0.961375,0.964498,0.960474,0.957903,0.961217,0.974986,0.982645,0.971134,0.9654,0.971506,0.968362,0.959732,0.957778,0.959508,0.967534,0.973178,0.965026,0.957126,0.958616,0.960802,0.961053,0.964084,0.96183,0.958114,0.965788,0.969459,0.960976,0.956887,0.958189,0.958964,0.958011,0.957533,0.957715,0.957691,0.957341,0.957624,0.957934,0.957806,0.957534,0.95783,0.957467,0.957649,0.95773,0.958123,0.959269,0.967814,0.971765,0.963337,0.962249,0.968157,0.965272,0.961768,0.962022,0.966465,0.980526,0.98378,0.974297,0.969102,0.965275,0.964145,0.963479,0.964419,0.970112,0.975036,0.981283,0.980407,0.969696,0.967267,0.970854,0.965962,0.964678,0.968106,0.970435,0.972122,0.977295,0.974225,0.971143,0.969861,0.966657,0.967168,0.969415,0.977914,0.981908,0.97235,0.963466,0.965001,0.969257,0.971543,0.96685,0.963841,0.968891,0.96998,0.966631,0.96246,0.961112,0.961783,0.961586,0.964643,0.96833,0.972733,0.970926,0.962542,0.960837,0.965393,0.965175,0.960876,0.965464,0.975273,0.975623,0.965196,0.960928,0.960485,0.963051,0.961693,0.959573,0.959079,0.959279,0.963469,0.969853,0.965149,0.960293,0.961586,0.961784,0.959597,0.960476,0.961289,0.963682,0.969437,0.966221,0.960111,0.958568,0.958594,0.958354,0.958081,0.960243,0.960777,0.95972,0.962294,0.963565,0.963706,0.964672,0.960951,0.959619,0.962409,0.963412,0.959969,0.958995,0.959166,0.959742,0.960086,0.962246,0.96211,0.96029,0.960392,0.960161,0.960185,0.962913,0.963179,0.961025,0.959746,0.959911,0.95976,0.959738,0.960072,0.960283,0.959888,0.959922,0.960567,0.962538,0.962885,0.961609,0.960426,0.960309,0.960258,0.960177,0.959924,0.96019,0.96032,0.960646,0.96114,0.961302,0.960865,0.960768,0.96067,0.960567,0.960544,0.960323,0.960009,0.960476,0.960701,0.960607,0.960497,0.96068,0.96081,0.960739,0.960813,0.961015,0.960943,0.961271,0.961384,0.961425,0.961219,0.961402,0.961521,0.961678,0.961814,0.961768,0.961744,0.961937,0.961796,0.961682,0.96138,0.961656,0.961918,0.961983,0.961808,0.961832,0.961563,0.961635,0.961813,0.962295,0.962338,0.962411,0.96249,0.962721,0.962459,0.962418,0.96266,0.962996,0.962799,0.962696,0.962189,0.96211,0.961837,0.961855,0.961789,0.962184,0.962255,0.962258,0.962181,0.962047,0.96192,0.962142,0.961581,0.961004,0.961099,0.961538,0.961129,0.961268,0.961413,0.961548,0.961289,0.961211,0.960784,0.96057,0.960637,0.96099,0.960934,0.961504,0.961461,0.961208,0.960887,0.960851,0.960171,0.960452,0.960787,0.960692,0.960775,0.961227,0.960997,0.960593,0.960508,0.960773,0.961049,0.961067,0.960662,0.960962,0.960967,0.960956,0.960665,0.96038,0.960075,0.960377,0.960335,0.960647,0.960591,0.960417,0.959994,0.960503,0.960539,0.960538,0.960325,0.960291,0.96027,0.959947,0.959974,0.960744,0.960781,0.961082,0.961272,0.961099,0.960698,0.960844,0.960673,0.960908,0.960752,0.960713,0.960654,0.960871,0.961067,0.961438,0.961192,0.961057,0.960901,0.960809,0.960698,0.961024,0.960878,0.960599,0.960564,0.960985,0.960643,0.960324,0.96026,0.960868,0.961144,0.961088,0.961056,0.961441,0.961362,0.961562,0.961709,0.961996,0.961742,0.961765,0.961715,0.962002,0.961973,0.962132,0.962103,0.962085,0.961627,0.961981,0.962098,0.962044,0.96226,0.962788,0.962897,0.963047,0.96294,0.963096,0.963056,0.963272,0.963361,0.963479,0.963325,0.963664,0.964146,0.964111,0.963864,0.964175,0.964127,0.964216,0.964482,0.964301,0.964274,0.964555,0.96495,0.965284,0.964904,0.965187,0.965317,0.965502,0.965611,0.965381,0.964916,0.965198,0.965225,0.965117,0.964938,0.965261,0.96552,0.965663,0.965723,0.965693,0.965382,0.965914,0.966189,0.966288,0.965876,0.965779,0.96596,0.966565,0.966287,0.966019,0.965926,0.966222,0.966406,0.9664,0.966182,0.966161,0.965855,0.966077,0.965937,0.966341,0.966585,0.966578,0.966386,0.966461,0.966225,0.966442,0.966382,0.966951,0.966895,0.966676,0.966611,0.966844,0.966786,0.967057,0.966989,0.967285,0.967259,0.967466,0.967007,0.967013,0.967339,0.9678,0.967647,0.967168,0.966569,0.966929,0.967094,0.967173,0.967015,0.967264,0.966942,0.967027,0.967151,0.967706,0.967555,0.967498,0.967435,0.967575,0.967256,0.967167,0.966939,0.96722,0.967033,0.966894,0.966808,0.967064,0.967201,0.967369,0.967361,0.967128,0.966673,0.966991,0.967464,0.967848,0.967513,0.967821,0.968204,0.968098,0.966859,0.96704,0.967606,0.967611,0.967216,0.967499,0.967348,0.967695,0.968116,0.96805,0.967792,0.968031,0.967447,0.967167,0.96749,0.967731,0.967874,0.968445,0.968098,0.968258,0.968464,0.968177,0.967561,0.967841,0.967849,0.967895,0.96791,0.967855,0.967641,0.9682,0.967914,0.968165,0.968382,0.968573,0.968314,0.96837,0.968321,0.96877,0.96895,0.969583,0.969204,0.967926,0.967541,0.969142,0.969256,0.968655,0.96825,0.968427,0.968394,0.96832,0.968069,0.968196,0.967504,0.964546,0.964023,0.968592,0.969131,0.969648,0.96922,0.969625,0.968915,0.969267,0.968876,0.97004};
		
	//Constructor
	public Spectra(String inputID, double[] inputSpectra){
		rawValues = inputSpectra;
		sampleID = inputID;
	}

	public double[] getRawvalues() {
		return rawValues;
	}

	private void setRawvalues(double[] rawValues) {
		this.rawValues = rawValues;
	}

	public String getSampleID() {
		return sampleID;
	}

	private void setSampleID(String sampleID) {
		this.sampleID = sampleID;
	}

	//Call when the correction factor has not been completed for Nicolet spectra
	public void correction(){
		double[] tempSpectrum = new double[Array.getLength(this.rawValues)];
		for(int i = 0;i<Array.getLength(this.rawValues);i++){
			double temp = this.rawValues[i] * this.corrValues[i];
			tempSpectrum[i] = temp;
		}
		setRawvalues(tempSpectrum);
	}
	
}