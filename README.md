# Hvordan i greb test drevet arbejde an?
- Vi startede med at køre alle tests og konstaterede at langt de fleste var røde.
- Derefter kiggede vi testene igennem fra toppen og bevægede os ned igennem én ad gangen.
- For hver test læste vi igennem Arrange-Act-Assert strukturen, for at finde ud af præcist hvad testen forventede, før vi skrev noget kode. Vi implementerede kun det der skulle til for at gøre testen grøn, og læste derefter den næste osv.

# Hvad der var sværest?
Der var ikke noget, der var sværest som sådan: Men det at man skulle finde ud af, hvad der skulle til for at få testen grøn, var det vi vurderede at sværest. Også det at skulle vurdere hvad er "det minimale" ift. kode der skulle implementeres er, is stedet for at skrive den rigtige løsning, man normalt ville gøre.

# Hvad i ville for bedre hvis i havde mere tid?
Vi havde meget tid til overs, da opgaven gik ret hurtigt. Når først vi havde gennemskuet fidusen, var det bare at køre der ud af, en test ad gangen. 

Men hvis vi skulle komme med noget, kunne vi selv have kommet med flere test-cases selv (var dog ikke del af opgaven). Ydeligere kunne man også overveje om StreamBoxService burde opdeles i flere klasser, da den nuværende klasse, ud over forretningslogik også håndtere lagring og validering af input. Hvor lagring fx hører mere hjemme i fx en ContentDao- eller ContentRepository-klasse, så service kun håndtere logikken. 