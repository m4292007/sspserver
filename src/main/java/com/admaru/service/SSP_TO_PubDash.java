package com.admaru.service;

public class SSP_TO_PubDash {

/*
 select day( log_ts ) dates,adspace_id, count(*) impression , sum(p1)/1000 gross_usd, sum(p2)/1000 netrevenue_usd, sum(p1*1250)/1000 gross_krw, sum(p2*1250)/1000 netrevenue_krw, 
sum(p2*1250)/ count(*)  cpm
from ssp_imp 
where site_id = '2022' and p_id = '1000'
and adspace_id in ('/30349040/JA_MO_article/mid/300x250v2', '/30349040/JA_MO_article/mid/300x250') 
and log_ts between '2022-06-01 00:00:00' and '2022-06-30 23:59:59'
group by day( log_ts ), adspace_id;


select day( log_ts ) dates,adspace_id, count(*) impression , sum(p1)/1000 gross_usd, sum(p2)/1000 netrevenue_usd, sum(p1*1250)/1000 gross_krw, sum(p2*1250)/1000 netrevenue_krw, 
sum(p2*1250)/ count(*)  cpm
from ssp_imp 
where p_id = '1000' 
and adspace_id in ('/106061858/WomanDonga_MB_NEWS_300x250_Display', '/106061858/ShinDonga_MB_NEWS_300x250_Display', '/106061858/WeeklyDonga_MB_NEWS_300x250_Display', '/106061858/BiznDonga_MB_NEWS_300x250_Display')
and log_ts between '2022-06-01 00:00:00' and '2022-06-30 23:59:59'
group by day( log_ts ), adspace_id;
 
 
 */
}
