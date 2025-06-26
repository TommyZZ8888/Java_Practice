-- 多次购买同一供货商      
SELECT county,
               town,
               village_collective, year, project_name, project_type, actual_balance, purchase_name, purchase_id_card, merchant_name, merchant_code, GROUP_CONCAT(DISTINCT id) AS data_id
        FROM  biz_nyj_sz_asset_purchase
        WHERE  purchase_date >= (SELECT MIN(purchase_date) FROM biz_nyj_sz_asset_purchase)
        GROUP BY  merchant_name
        HAVING
            COUNT(*) > (
            SELECT AVG(monthly_count)
            FROM (
            SELECT  merchant_name,  COUNT(*) AS monthly_count
            FROM  biz_nyj_sz_asset_purchase
            GROUP BY  merchant_name,  DATE_FORMAT(purchase_date, '%Y-%m')) AS monthly_purchases )
           AND
            EXISTS (
            SELECT 1
            FROM biz_nyj_sz_asset_purchase sub
            WHERE sub.merchant_name = biz_nyj_sz_asset_purchase.merchant_name
           AND sub.purchase_date BETWEEN sub.purchase_date AND DATE_ADD(sub.purchase_date, INTERVAL 1 MONTH)
            GROUP BY
            sub.merchant_name,
            DATE_FORMAT(sub.purchase_date, '%Y-%m')
            HAVING COUNT(*) > 1
            );


-- 与非特困户比对
        select max(STR_TO_DATE(t2.grant_time, '%Y-%m-%d'))  grant_time,
               t2.id_card                                  id_card,
               t2.name                                     name,
               sum(t2.actual_grant_amount)                 actual_grant_amount,
               NOW()                                       comparison_update_date,
               sum(t2.grant_countnum)                      grant_countnum,
               GROUP_CONCAT(t2.id)                         data_id
        from biz_ykt_fszybz t2
        WHERE NOT EXISTS (
            SELECT 1
            FROM biz_mz_destitute t
            WHERE t.card = t2.id_card AND t.name = t2.name
        )
        group by t2.id_card,t2.name